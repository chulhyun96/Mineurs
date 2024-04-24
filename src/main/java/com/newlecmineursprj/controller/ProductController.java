package com.newlecmineursprj.controller;

import com.newlecmineursprj.entity.*;
import com.newlecmineursprj.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ColorService colorService;
    private final SizeService sizeService;
    private final ProductSubImgService productSubImgService;
    private final CategoryService categoryService;

    @GetMapping("{id}")
    public String list(@PathVariable long id
    , Model model){

        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList",categoryList);

        Product product = service.getById(id);
        model.addAttribute("product", product);

        List<Color> colors = colorService.getList(id);
        model.addAttribute("colors", colors);

        List<Size> sizes = sizeService.getList(id);
        model.addAttribute("sizes", sizes);

        List<ProductSubImg> subImgs = productSubImgService.getListByProductId(id);
        model.addAttribute("subImgs", subImgs);

        return "detail";
    }
}
