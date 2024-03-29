package com.newlecmineursprj.controller.admin;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.service.CategoryService;
import com.newlecmineursprj.service.DetailImgService;
import com.newlecmineursprj.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("admin/products")
@Controller("adminProductController")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private static final String PRODUCTS_VIEW = "/admin/products";
    private static final String REDIRECT = "redirect:";

    private final ProductService service;
    private final CategoryService categoryService;
    private final DetailImgService detailImgService;

    @GetMapping
    public String list(Model model) {
        List<ProductView> list = service.getList();
        model.addAttribute("list", list);
        return PRODUCTS_VIEW + "/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ProductView product = service.getById(id);
        model.addAttribute("product", product);
        return PRODUCTS_VIEW + "/detail";
    }
    @GetMapping("/reg")
    public String regForm(Model model) {
        List<Category> categories = categoryService.getList();
        model.addAttribute("categories", categories);
        return PRODUCTS_VIEW + "/reg";
    }

    //Img doesnt change
    @PutMapping
    @ResponseBody
    public String edit(@RequestBody Product product) {
        service.edit(product);
        return "success";
    }

    @PostMapping
    public String reg(@ModelAttribute Product product, Long categoryId, String paths) {
        product.setCategoryId(categoryId);
        service.reg(product);
        detailImgService.regAll(paths, product.getId());
        return REDIRECT + PRODUCTS_VIEW;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam List<Long> deleteId) {
        service.deleteAllById(deleteId);
        log.info("deleteId =" + deleteId);
        return REDIRECT + PRODUCTS_VIEW;
    }
}
