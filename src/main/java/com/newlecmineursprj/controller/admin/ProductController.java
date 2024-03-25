package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.entity.ProductEntity;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin/products")
@Controller("adminProductController")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public String list(Model model) {
        List<ProductView> list = service.getList();
        log.info("list: {}", list);
        model.addAttribute("list", list);
        return "admin/products/list";

    }

    @GetMapping("{id}")
    public String detail(@PathVariable Long id, Model model) {
        ProductView product = service.getById(id);
        model.addAttribute("product", product);
        return "admin/products/detail";
    }
     @PostMapping
     public String reg(@ModelAttribute ProductEntity product) {
         log.info("product = {}", product.getName());
         return "redirect:/admin/products";
     }

    @GetMapping("/reg")
    public String regForm() {
        return "admin/products/reg";
    }
}
