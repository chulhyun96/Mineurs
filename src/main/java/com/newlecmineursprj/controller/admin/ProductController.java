package com.newlecmineursprj.controller.admin;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.service.CategoryService;
import com.newlecmineursprj.service.ProductSubImgService;
import com.newlecmineursprj.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("admin/products")
@Controller("adminProductController")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private static final String PRODUCTS_VIEW = "/admin/products";
    private static final String REDIRECT = "redirect:";
    private static final String LIST_VIEW = PRODUCTS_VIEW + "/list";

    private final ProductService service;
    private final CategoryService categoryService;
    private final ProductSubImgService productSubImgService;


    @GetMapping
    public String clist(@RequestParam(required = false) String searchMethod
            , @RequestParam(defaultValue = "") String searchKeyword
            , Model model) {

        String trimSearchKeyword = searchKeyword.trim();

        if (searchMethod == null) {
            List<ProductView> list = service.getList();
            model.addAttribute("list", list);
            return LIST_VIEW;
        }

        List<ProductView> list = service.getList(searchMethod, trimSearchKeyword);
        model.addAttribute("list", list);
        return LIST_VIEW;
    }

    @GetMapping("/reg")
    public String regForm(Model model) {
        List<Category> categories = categoryService.getList();
        model.addAttribute("categories", categories);
        return PRODUCTS_VIEW + "/reg";
    }

    @PostMapping
    public String reg(Product product, Long categoryId, String paths) {
        product.setCategoryId(categoryId);
        service.reg(product);
        productSubImgService.regAll(paths, product.getId());
        return REDIRECT + PRODUCTS_VIEW;
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ProductView product = service.getById(id);
        model.addAttribute("product", product);
        return PRODUCTS_VIEW + "/detail";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam List<Long> deleteId) {
        service.deleteAllById(deleteId);
        log.info("deleteId =" + deleteId);
        return REDIRECT + PRODUCTS_VIEW;
    }

    @PutMapping
    public String edit(@RequestBody Product product) {
        service.edit(product);
        return "success";
    }

}
