package com.brainmatics.controllers;

import java.util.List;

import com.brainmatics.data.entity.Product;
import com.brainmatics.data.repos.ProductFakeRepo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private ProductFakeRepo repo = new ProductFakeRepo();
    
    @GetMapping
    public String main(Model model){
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/products/{code}")
    public String detail(Model model, @PathVariable("code") String code){
        Product product = repo.findOne(code);
        model.addAttribute("product", product);
        return "detail";
    }

    @GetMapping("/products/add")
    public String add(Model model){
        model.addAttribute("product", new Product());
        return "input";
    }

    @PostMapping("/products/save")
    public String save(Product product){
        repo.createOne(product);
        return "redirect:/";
    }


}
