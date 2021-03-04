package com.computerAccessoriesStore.controller.product;

import com.computerAccessoriesStore.models.Product;
import com.computerAccessoriesStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/showProduct")
    public String showProduct(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/showProduct";
    }

    @GetMapping(value = "/findProduct")
    public String findProduct(@RequestParam(value = "search", required = false, defaultValue = "") String name, Model model){
        List<Product>findProduct =productService.getProductsByName(name);
        model.addAttribute("result", findProduct);
        model.addAttribute("search", name);
        return "admin/product/findProduct";
    }
}
