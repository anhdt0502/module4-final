package com.fashion.controller;

import com.fashion.entity.Category;
import com.fashion.entity.Product;
import com.fashion.service.CategoryService;
import com.fashion.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ProductPageController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final String uploadDir =
            "uploads/products";

    public ProductPageController(
            ProductService productService,
            CategoryService categoryService) {

        this.productService = productService;
        this.categoryService = categoryService;
    }

    // =========================
    // DANH SÁCH SẢN PHẨM
    // =========================

    @GetMapping("/products")
    public String products(Model model) {

        model.addAttribute(
                "products",
                productService.findAll()
        );

        return "products";
    }


    // =========================
    // FORM THÊM
    // =========================

    @GetMapping("/products/create")
    public String createForm(Model model) {

        model.addAttribute(
                "product",
                new Product()
        );

        model.addAttribute(
                "categories",
                categoryService.findAll()
        );

        return "product-form";
    }


    // =========================
    // THÊM SẢN PHẨM
    // =========================

    @PostMapping("/products/create")
    public String createProduct(
            @ModelAttribute Product product,
            @RequestParam Long categoryId,
            @RequestParam(required = false) MultipartFile image)
            throws IOException {

        Category category =
                categoryService.findById(categoryId);

        product.setCategory(category);

        // Upload ảnh
        if (image != null && !image.isEmpty()) {

            String imageUrl = saveImage(image);

            product.setImageUrl(imageUrl);
        }

        productService.create(product);

        return "redirect:/products";
    }


    // =========================
    // FORM SỬA
    // =========================

    @GetMapping("/products/edit/{id}")
    public String editForm(
            @PathVariable Long id,
            Model model) {

        Product product =
                productService.findById(id);

        model.addAttribute(
                "product",
                product
        );

        model.addAttribute(
                "categories",
                categoryService.findAll()
        );

        return "product-form";
    }


    // =========================
    // SỬA SẢN PHẨM
    // =========================

    @PostMapping("/products/edit/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute Product product,
            @RequestParam Long categoryId,
            @RequestParam(required = false) MultipartFile image)
            throws IOException {

        Product oldProduct =
                productService.findById(id);

        Category category =
                categoryService.findById(categoryId);

        product.setCategory(category);

        // Nếu chọn ảnh mới
        if (image != null && !image.isEmpty()) {

            String imageUrl = saveImage(image);

            product.setImageUrl(imageUrl);

        } else {

            // Không chọn ảnh mới → giữ ảnh cũ
            product.setImageUrl(
                    oldProduct.getImageUrl()
            );
        }

        productService.update(id, product);

        return "redirect:/products";
    }


    // =========================
    // XÓA
    // =========================

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id) {

        productService.delete(id);

        return "redirect:/products";
    }


    // =========================
    // LƯU FILE ẢNH
    // =========================

    private String saveImage(
            MultipartFile image)
            throws IOException {

        Path uploadPath =
                Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalName =
                image.getOriginalFilename();

        String extension = "";

        if (originalName != null
                && originalName.contains(".")) {

            extension =
                    originalName.substring(
                            originalName.lastIndexOf(".")
                    );
        }

        String fileName =
                UUID.randomUUID()
                        + extension;

        Path filePath =
                uploadPath.resolve(fileName);

        Files.copy(
                image.getInputStream(),
                filePath
        );

        return "/uploads/products/" + fileName;
    }
}