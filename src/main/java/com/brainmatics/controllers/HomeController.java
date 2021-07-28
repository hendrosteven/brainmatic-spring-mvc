package com.brainmatics.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.brainmatics.data.entity.Product;
import com.brainmatics.data.repos.CategoryRepo;
import com.brainmatics.data.repos.ProductRepo;
import com.brainmatics.dto.MessageData;
import com.brainmatics.dto.ProductData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductRepo repo;

    @Autowired
    private CategoryRepo categoryRepo;

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
        model.addAttribute("product", new ProductData());
        model.addAttribute("categories", categoryRepo.findAll());
        return "input";
    }

    @PostMapping("/products/save")
    public String save(@Valid ProductData productData, BindingResult resultErrors, Model model) {

        if(resultErrors.hasErrors()) {
            MessageData errorMessage = new MessageData();
            for(ObjectError err: resultErrors.getAllErrors()) {
                errorMessage.getMessages().add(err.getDefaultMessage());
            }
            model.addAttribute("product", productData);
            model.addAttribute("ERROR", errorMessage);
            model.addAttribute("categories", categoryRepo.findAll());
            return "input";
        }

        Product product = new Product();
        product.setCode(productData.getCode());
        product.setName(productData.getName());
        product.setPrice(productData.getPrice());
        product.setDescription(productData.getDescription());
        product.setCategory(categoryRepo.findById(productData.getCategoryId()).get());
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
