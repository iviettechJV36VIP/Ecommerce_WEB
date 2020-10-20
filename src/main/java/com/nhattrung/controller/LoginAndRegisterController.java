/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.controller;

import com.nhattrung.entity.AccountRole;
import com.nhattrung.entity.ConfirmationToken;
import com.nhattrung.entity.Customer;
import com.nhattrung.repository.AccountRoleRepository;
import com.nhattrung.repository.ConfirmationTokenRepository;
import com.nhattrung.repository.CustomerRepository;
import com.nhattrung.service.EmailService;
import com.nhattrung.service.Md5;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;



import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginAndRegisterController {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AccountRoleRepository accountRoleRepository;
    
    @Autowired
    private Md5 md5;
    
    @RequestMapping(value="/admin")
    public String showAdminPage(Model model){
        return "admin/admin";
    }
    @RequestMapping(value="/customer")
    public String showCustomerPage(Model model){
        return "customer/customer";
    }
    
    @RequestMapping(value="/staff")
    public String showStaffPage(Model model){
        return "staff/staff";
    }
    
    @GetMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    @GetMapping(value = "/403")
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

    @GetMapping("/register")

    public String showFormRegister(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "register";
    }

    @PostMapping(value = "/register")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer, ModelAndView modelAndView) {

        Customer existingUser = customerRepository.findByEmail(customer.getEmail());
        if (existingUser != null) {
            modelAndView.addObject("message", "This email already exists!");
            modelAndView.setViewName("error");
        } else {
            String password = customer.getPassword();
            customer.setPassword(md5.md5(password));
            customerRepository.save(customer);          
            ConfirmationToken confirmationToken = new ConfirmationToken(customer);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(customer.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("phanhuynhat1991@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:8080/Ecommerce_Web/confirmAccount?token=" + confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);

            modelAndView.addObject("email", customer.getEmail());

            modelAndView.setViewName("registrationSuccess");
        }

        return modelAndView;
    }
    
    @RequestMapping(value="/confirmAccount", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            Customer customer = customerRepository.findByEmail(token.getCustomer().getEmail());
            customer.setEnabled(true);
            customerRepository.save(customer);
            AccountRole accountRole = new AccountRole();
            accountRole.setCustomer(customer);
            accountRoleRepository.save(accountRole);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
    

}
