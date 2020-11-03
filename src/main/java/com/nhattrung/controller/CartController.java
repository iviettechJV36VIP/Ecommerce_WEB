/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.controller;


import com.nhattrung.entity.Customer;
import com.nhattrung.entity.Item;
import com.nhattrung.entity.OrderDetails;
import com.nhattrung.entity.OrderList;
import com.nhattrung.entity.Payment;
import com.nhattrung.entity.Paypal;
import com.nhattrung.repository.ConfirmationTokenRepository;
import com.nhattrung.repository.CustomerRepository;
import com.nhattrung.service.CategoryService;
import com.nhattrung.service.CustomerService;
import com.nhattrung.service.EmailService;
import com.nhattrung.service.Md5;
import com.nhattrung.service.OrderDetailsService;
import com.nhattrung.service.OrderListService;
import com.nhattrung.service.PaymentService;
import com.nhattrung.service.PaypalService;
import com.nhattrung.service.ProducerService;
import com.nhattrung.service.ProductService;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@RequestMapping(value = "/")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderListService orderListService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private Md5 md5;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
    public String index(HttpSession session, ModelMap modelMap) {
        int countItems = 0;
        int total = 0;
        if (session.getAttribute("cart") != null) {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            countItems = cart.size();
            for (Item item : cart) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat c = NumberFormat.getCurrencyInstance(localeVN);
        modelMap.put("countItems", countItems);
        modelMap.put("total", c.format(total));
        return "cart";
    }

    @RequestMapping(value = "/buy/{productId}", method = RequestMethod.GET)
    public String buy(@PathVariable("productId") int productId, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(productService.getProductDetails(productId), 1));
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = this.exists(productId, cart);
            if (index == -1) {
                cart.add(new Item(productService.getProductDetails(productId), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/checkout", method = RequestMethod.GET)
    public String checkout(Authentication authentication, HttpSession session, ModelMap modelMap, ModelAndView modelAndView) {

        int countItems = 0;
        int total = 0;
        if (session.getAttribute("cart") != null) {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            countItems = cart.size();
            for (Item item : cart) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat c = NumberFormat.getCurrencyInstance(localeVN);
        modelMap.put("total", c.format(total));

        return "checkout";

    }

    @RequestMapping(value = "/cart/checkout/thankyou", method = RequestMethod.GET)
    public String saveInvoice(@RequestParam(value = "error", required = false) String error, @RequestParam("code") int code, @RequestParam("name") String name, @RequestParam("month") int month,
            @RequestParam("year") int year, @RequestParam("securityCode") int securityCode,
            HttpSession session, ModelMap modelMap, ModelAndView modelAndView, Model theModel) {

        int countItems = 0;
        int total = 0;
        if (session.getAttribute("cart") != null) {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            countItems = cart.size();
            for (Item item : cart) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat c = NumberFormat.getCurrencyInstance(localeVN);
        NumberFormat d = NumberFormat.getCurrencyInstance();
        modelMap.put("total", c.format(total));

        boolean check = false;
        String messenger = null;
        List<Paypal> paypals = paypalService.getAllPaypal();
        Paypal paypal = null;
        for (Paypal p : paypals) {
            if (code == p.getPaypalCode() && name.equals(p.getPaypalName())
                    && month == p.getPaypalMonth() && year == p.getPaypalYear() && securityCode == p.getPaypalSecurityCode()) {
                paypal = p;
                break;
            } 
        }
        String messenger1 = null;
        if(paypal == null){
            messenger = "Information not correct!!!";
        }else if (paypal.getPaypalMoney() >= total) {
            int newMoney = paypal.getPaypalMoney() - total;

            paypal.setPaypalMoney(newMoney);

            OrderList orderList = new OrderList();
            orderList.setAmount(total);
            Customer customer = customerService.getCustomerDetails();
            orderList.setOrderDate(LocalDate.now());
            orderList.setCustomer(customer);
            orderList = orderListService.save(orderList);

            List<Item> cart = (List<Item>) session.getAttribute("cart");
            for (Item item : cart) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderLists(orderList);
                orderDetails.setProduct(item.getProduct());
                orderDetails.setQuantity(item.getQuantity());
                orderDetailsService.save(orderDetails);
            }

            Payment payment = new Payment();
            payment.setOrderLists(orderList);
            payment.setPaypal(paypal);
            payment.setAmount(total);
            payment.setPaymentDate(LocalDate.now());
            paymentService.save(payment);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(customer.getEmail());
            mailMessage.setSubject("Complete Checkout!");
            mailMessage.setFrom("furushuriya@gmail.com");
            mailMessage.setText("Thank you Mr/Mrs " + customer.getCustomerFirstName() + customer.getCustomerLastName() + " for your buying at our website\n"
                    + "Your order id is: " + orderList.getOrderId() + "\n"
                    + "Order date: " + orderList.getOrderDate() + "\n"
                    + "Total amount: " + d.format(total) + "vnd" + "\n"
                    + "We will ship your order to your address at " + customer.getCustomerAddress() + "\n"
                    + "And we will call to your phone(" + customer.getPhone() + ") to check" + "\n"
                    + "We hope you will support us more in the future" + "\n"
                    + "Thank you very much!!");
            
            emailService.sendEmail(mailMessage);
            check = true;
        }else{
            messenger1 = "You not have enough money, please check your card!!!";
        }

        if (check == true) {
            return "thankyou";
        } else {
            theModel.addAttribute("messenger", messenger);
            theModel.addAttribute("messenger1", messenger1);
            return "checkout";
        }
    }

    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.GET)
    public String remove(@PathVariable("productId") int productId, HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = this.exists(productId, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    private int exists(int productId, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getProductId() == productId) {
                return i;
            }
        }
        return -1;
    }

}
