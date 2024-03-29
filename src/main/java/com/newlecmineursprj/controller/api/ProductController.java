package com.newlecmineursprj.controller.api;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/products")
@RequiredArgsConstructor
@RestController
public class ProductController {
    private static final String PRODUCTS_VIEW = "/admin/products";
    private final ProductService service;
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ProductView product = service.getById(id);
        model.addAttribute("product", product);
        return PRODUCTS_VIEW + "/detail";
    }
    @PutMapping
    public String edit(@RequestBody Product product) {
        service.edit(product);
        return "success";
    }
}
