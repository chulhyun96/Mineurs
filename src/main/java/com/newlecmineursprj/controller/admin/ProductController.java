package com.newlecmineursprj.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.service.CategoryService;
import com.newlecmineursprj.service.DetailImgService;
import com.newlecmineursprj.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("admin/products")
@Controller("adminProductController")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DetailImgService detailImgService;

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
    public String reg(@ModelAttribute Product product, Long categoryId, String paths) {
        product.setCategoryId(categoryId);
        service.reg(product);
        Long num = product.getId();

        detailImgService.regAll(paths, num);

        log.info("category = {}", categoryId);
        log.info("product = {}", product);


        return "redirect:/admin/products";
    }

    @GetMapping("/reg")
    public String regForm(Model model) {
        List<Category> categories = categoryService.getList();
        model.addAttribute("categories", categories);
        return "admin/products/reg";
    }
}
