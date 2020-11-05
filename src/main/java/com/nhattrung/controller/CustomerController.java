/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.controller;

import com.nhattrung.entity.AccountRole;

import com.nhattrung.entity.Customer;
import com.nhattrung.entity.OrderList;
import com.nhattrung.repository.AccountRoleRepository;

import com.nhattrung.repository.CategoryRepository;
import com.nhattrung.repository.ConfirmationTokenRepository;
import com.nhattrung.repository.CustomerRepository;
import com.nhattrung.repository.ImageRepository;
import com.nhattrung.repository.OrderListRepository;
import com.nhattrung.repository.ProducerRepository;
import com.nhattrung.service.CustomerService;
import com.nhattrung.service.Md5Service;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
@Transactional(noRollbackFor = Exception.class)
public class CustomerController {

    @Autowired
    ServletContext servletContext;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Autowired
    Md5Service md5Service;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    
    @Autowired
    OrderListRepository orderListRepository;

    @GetMapping(value = {"", "/showCustomer", "/showCustomer/{page}"})
    public String listAllCustomers(@PathVariable(required = false, name = "page") String page, HttpServletRequest request, HttpServletResponse response, Model model) {

        PagedListHolder<Customer> customerList;
        if (page == null) {
            customerList = new PagedListHolder<Customer>();
            List<Customer> customers = (List) customerRepository.findAll();

            customerList.setSource(customers);
            customerList.setPageSize(10);

            request.getSession().setAttribute("customerList", customerList);
        } else if (page.equals("prev")) {

            customerList = (PagedListHolder<Customer>) request.getSession().getAttribute("customerList");

            customerList.previousPage();
        } else if (page.equals("next")) {
            customerList = (PagedListHolder<Customer>) request.getSession().getAttribute("customerList");

            customerList.nextPage();
        } else {
            int pageNum = Integer.parseInt(page);
            customerList = (PagedListHolder<Customer>) request.getSession().getAttribute("customerList");

            customerList.setPage(pageNum - 1);
        }

        model.addAttribute("customerList", customerList);
        return "admin/viewAllCustomer";
    }

    @RequestMapping(value = "/addNewCustomer", method = RequestMethod.GET)
    public String showFormForAdd(Model model) {
        List<Customer> customers = (List) customerRepository.findAll();
        List<AccountRole> accountRoles = (List) accountRoleRepository.findAll();
        AccountRole accountRole = new AccountRole();
        Customer customer = new Customer();
        model.addAttribute("customers", customers);
        model.addAttribute("accountRoles", accountRoles);
        model.addAttribute("accountRole", accountRole);
        model.addAttribute("customer", customer);

        return "admin/addNewCustomer";
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer customer, @RequestParam("accountRoles[0].roleName") String roleName) {

        String password = customer.getPassword();
        customer.setPassword(md5Service.md5(password));
        customerRepository.save(customer);
        AccountRole accountRole = new AccountRole();
        accountRole.setCustomer(customer);
        accountRole.setRoleName(roleName);
        accountRoleRepository.save(accountRole);
        return "redirect:/showCustomer";
    }

    @GetMapping(value = "/searchCustomer")
    public String searchCustomer(@RequestParam("searchCustomer") String searchText, HttpServletRequest request, Model theModel) {
        List<Customer> customerList = customerService.getListCustomersSearch(searchText);

        theModel.addAttribute("customerList", customerList);

        return "admin/resultSearchCustomer";
        //return "admin/admin";
    }

    
    @GetMapping("/deleteCustomer/{customerId}")
    public String deleteCustomer(@PathVariable int customerId, Model model) {
        //Customer customer = customerRepository.findByCustomerId(customerId);
        List<OrderList> orderLists = orderListRepository.findOrderListByCustomerId(customerId);
        String messenger = null;
        if(orderLists.isEmpty()){
            customerRepository.deleteById(customerId);
            return "redirect:/showCustomer";
        } else {
            messenger = "Cannot delete this customer because ordered";
            model.addAttribute("messenger", messenger);
            return "admin/viewAllCustomer";  
        }
        
        /*try { 
            int tokenId = customer.getConfirmationToken().getTokenId();
            
            confirmationTokenRepository.deleteById(tokenId);
            customerRepository.deleteById(customerId);
        } catch (Exception e) {
            customerRepository.deleteById(customerId);
        }*/
        
    }
    
    @GetMapping(value = "/profile")
    public String gotoPageAccount(Model model){
        Customer customer = customerService.getCustomerDetails();
        model.addAttribute("customer", customer);
        return "/customer/profileCustomer";
    }
    
    @GetMapping("/editAccount/{customerId}")
    public String gotoPageEditAccount(@PathVariable("customerId") int customerId, Model model){
        Customer customer =  customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "customer/editCustomer";
    }
    
    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public String editCustomer(@ModelAttribute("customer") Customer customer,@RequestParam("customerId") int customerId) {
        
        Customer theCustomer = customerRepository.findByCustomerId(customerId);
        
        String password = customer.getPassword();
        theCustomer.setCustomerFirstName(customer.getCustomerFirstName());
        theCustomer.setCustomerLastName(customer.getCustomerLastName());
        theCustomer.setSex(customer.getSex());
        theCustomer.setEmail(customer.getEmail());
        theCustomer.setPhone(customer.getPhone());
        theCustomer.setCustomerAddress(customer.getCustomerAddress());
        theCustomer.setPassword(md5Service.md5(password));
        customerRepository.save(theCustomer);
        
        
        return "redirect:/profile";
    }
}
