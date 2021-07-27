package com.brainmatics.controllers;

import java.util.Optional;

import com.brainmatics.data.entity.Product;
import com.brainmatics.data.repos.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductRepo repo;

    @GetMapping
    public String main(Model model) {
        Iterable<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/products/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Optional<Product> product = repo.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        }
        return "detail";
    }

    @GetMapping("/products/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "input";
    }

    @PostMapping("/products/save")
    public String save(Product product) {
        repo.save(product);
        return "redirect:/";
    }

    @GetMapping("/products/remove/{id}")
    public String remove(Model model, @PathVariable("id") Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/products/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<Product> product = repo.findById(id);
        if(product.isPresent()) {
            model.addAttribute("product", product);
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/products/update")
    public String update(Product product) {
        repo.save(product);
        return "redirect:/";
    }

}
