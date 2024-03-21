package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.entity.ProductEntity;
import com.newlecmineursprj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin/products")
@Controller("adminProductController")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public String list(Model model) {
        List<ProductEntity> list = service.getList();
        model.addAttribute("list", list);
        return "admin/products/list";
    }
    @PostMapping
    public String reg(@ModelAttribute ProductEntity product) {
        System.out.println("entity = " + product);
        return "redirect:/admin/products";
    }
    @GetMapping("/reg")
    public String regForm() {

        return "admin/products/reg";
    }
}
