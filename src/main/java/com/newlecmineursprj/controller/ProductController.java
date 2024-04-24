package com.newlecmineursprj.controller;

import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductSubImg;
import com.newlecmineursprj.entity.Size;
import com.newlecmineursprj.service.ColorService;
import com.newlecmineursprj.service.ProductService;
import com.newlecmineursprj.service.ProductSubImgService;
import com.newlecmineursprj.service.SizeService;
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

    @GetMapping("{id}")
    public String list(@PathVariable long id
    , Model model){

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
