package com.fashion.controller;

import com.fashion.entity.Category;
import com.fashion.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryPageController {

    private final CategoryService categoryService;

    public CategoryPageController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String categories(Model model) {

        model.addAttribute(
                "categories",
                categoryService.findAll()
        );

        return "categories";
    }

    @GetMapping("/categories/create")
    public String createForm(Model model) {

        model.addAttribute("category", new Category());

        return "category-form";
    }

    @PostMapping("/categories/create")
    public String createCategory(
            @ModelAttribute Category category) {

        categoryService.create(category);

        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String editForm(
            @PathVariable Long id,
            Model model) {

        Category category = categoryService.findById(id);

        model.addAttribute("category", category);

        return "category-form";
    }

    @PostMapping("/categories/edit/{id}")
    public String updateCategory(
            @PathVariable Long id,
            @ModelAttribute Category category) {

        categoryService.update(id, category);

        return "redirect:/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(
            @PathVariable Long id) {

        categoryService.delete(id);

        return "redirect:/categories";
    }
}