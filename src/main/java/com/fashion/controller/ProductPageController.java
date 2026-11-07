package com.fashion.controller;

import com.fashion.entity.Category;
import com.fashion.entity.Product;
import com.fashion.service.CategoryService;
import com.fashion.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductPageController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductPageController(
            ProductService productService,
            CategoryService categoryService) {

        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/products/create")
    public String createForm(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());

        return "product-form";
    }

    @PostMapping("/products/create")
    public String createProduct(
            @ModelAttribute Product product,
            @RequestParam Long categoryId) {

        Category category = categoryService.findById(categoryId);

        product.setCategory(category);

        productService.create(product);

        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editForm(
            @PathVariable Long id,
            Model model) {

        Product product = productService.findById(id);

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());

        return "product-form";
    }

    @PostMapping("/products/edit/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute Product product,
            @RequestParam Long categoryId) {

        Category category = categoryService.findById(categoryId);

        product.setCategory(category);

        productService.update(id, product);

        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.delete(id);

        return "redirect:/products";
    }
}