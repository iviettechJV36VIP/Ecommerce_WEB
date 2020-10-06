/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.controller;

import com.nhattrung.entity.Customer;
import com.nhattrung.repository.CustomerRepository;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Dell
 */
@Controller
public class LoginAndRegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsManager userDetailsManager; 
    
    @Autowired
    private CustomerRepository customerRepository;

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
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "register";
        }
        String hashedPassword = passwordEncoder.encode(theCustomer.getPassword());
        Collection<? extends GrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(theCustomer.getUsername(), hashedPassword, roles);
        userDetailsManager.createUser(userDetails);

        return "registerSuccess";
       /* Customer customer = new Customer();
        customer.setCustomerFirstName(theCustomer.getCustomerFirstName());
        customer.setCustomerLastName(theCustomer.getCustomerLastName());
        customer.setEmail(theCustomer.getEmail());
        customer.setUsername(theCustomer.getUsername());
        customer.setPassword(passwordEncoder.encode(theCustomer.getPassword()));

         customerRepository.save(customer);
         
         return "registerSuccess";*/
    }
}
