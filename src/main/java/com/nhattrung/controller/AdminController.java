package com.nhattrung.controller;

import com.nhattrung.entity.Category;
import com.nhattrung.entity.Image;
import com.nhattrung.entity.Producer;
import com.nhattrung.entity.Product;
import com.nhattrung.repository.CategoryRepository;
import com.nhattrung.repository.ImageRepository;
import com.nhattrung.repository.ProducerRepository;
import com.nhattrung.repository.ProductRepository;
import com.nhattrung.service.ProductService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/")
@Transactional(rollbackFor = Exception.class)
public class AdminController {

    @Autowired
    ServletContext servletContext;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    ImageRepository imageRepository;

    @GetMapping(value = {"", "/showProduct", "/showProduct/{page}"})
    
    public String listAllProducts(@PathVariable(required = false, name = "page") String page, HttpServletRequest request, HttpServletResponse response, Model model) {

        PagedListHolder<Product> productList;
        if (page == null) {
            productList = new PagedListHolder<Product>();
            List<Product> products = (List) productRepository.findAll();

            productList.setSource(products);
            productList.setPageSize(10);

            request.getSession().setAttribute("productList", productList);
        } else if (page.equals("prev")) {

            productList = (PagedListHolder<Product>) request.getSession().getAttribute("productList");

            productList.previousPage();
        } else if (page.equals("next")) {
            productList = (PagedListHolder<Product>) request.getSession().getAttribute("productList");

            productList.nextPage();
        } else {
            int pageNum = Integer.parseInt(page);
            productList = (PagedListHolder<Product>) request.getSession().getAttribute("productList");

            productList.setPage(pageNum - 1);
        }
        model.addAttribute("productList", productList);
        return "admin/admin";
    }

    @RequestMapping(value = "/addNewProduct", method = RequestMethod.GET)
    public String showFormForAdd(Model model) {
        Product product = new Product();
        List<Category> categoryList = (List) categoryRepository.findAll();
        List<Producer> producerList = (List) producerRepository.findAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("producerList", producerList);
        model.addAttribute("product", product);

        return "admin/addNewProduct";
    }

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    
    public String saveProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);

        return "redirect:/showProduct";
    }

    @GetMapping(value = "/searchProduct")
    public String searchProduct(@RequestParam("searchProduct") String searchText, HttpServletRequest request, Model theModel) {
        List<Product> productList = productService.getListProductsSearch(searchText);

        theModel.addAttribute("productList", productList);

        return "admin/resultSearchProduct";
        //return "admin/admin";
    }

    @GetMapping("/editProduct/{productId}")
    public String showFormEditForUpdate(@PathVariable int productId,
            Model model) {
        Product product = productService.getProductByProductId(productId);

        List<Category> editCategoryList = (List) categoryRepository.findAll();
        List<Producer> editProducerList = (List) producerRepository.findAll();

        model.addAttribute("categoryList", editCategoryList);
        model.addAttribute("producerList", editProducerList);

        model.addAttribute("product", product);
        return "admin/addNewProduct";
    }

    @RequestMapping(value = "/formUpload", method = RequestMethod.GET)
    public String showFormUpload(Model model) {
        Image image = new Image();
        model.addAttribute("image", image);
        return "admin/upload";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    String uploadFileHandker(@RequestParam("file") MultipartFile file, @ModelAttribute("image") Image image) {
        try {
            byte[] bytes = file.getBytes();
            String pathName = servletContext.getRealPath("/resources/images/test");
            File dir = new File(pathName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            imageRepository.save(image);
            String fileSource = pathName + File.separator + file.getOriginalFilename();
            File serverFile = new File(fileSource);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return "You upload sucessfully a file, at: " + fileSource;
        } catch (Exception e) {
            System.out.println(e);
            return "Error when uploading file " + e;
        }
    }
    
    @GetMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        //imageRepository.deleteByProductId(productId);
        productRepository.deleteById(productId);
        return "redirect:/showProduct";
    }
}
