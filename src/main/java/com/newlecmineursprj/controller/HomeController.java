package com.newlecmineursprj.controller;

import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.service.CategoryService;
import com.newlecmineursprj.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

private final ProductService service;
private final CategoryService categoryService;

    @GetMapping
    public String index(@RequestParam(required = false) String searchMethod
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "") String searchKeyword
            , Model model) {

        int count = service.getCount(searchMethod,searchKeyword);
        List<ProductView> list = service.getList(page, searchMethod, searchKeyword);
        List<Category> categoryList = categoryService.getList();
        model.addAttribute("list", list);
        model.addAttribute("count", count);
        model.addAttribute("categoryList",categoryList);
        System.out.println(list.get(0));
        return "list";
    }
}
