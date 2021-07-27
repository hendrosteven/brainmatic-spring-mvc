package com.brainmatics.controllers;

import com.brainmatics.data.entity.Category;
import com.brainmatics.data.repos.CategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategoryRepo repo;

    @GetMapping
    public String main(Model model) {
        Iterable<Category> categories = repo.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

}
