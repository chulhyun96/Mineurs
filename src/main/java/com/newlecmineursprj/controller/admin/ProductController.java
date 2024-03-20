package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.entity.ProductEntity;
import com.newlecmineursprj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("admin/products")
@Controller("adminProductController")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping
    public String string(Model model) {

        List<ProductEntity> list = service.getList();
        model.addAttribute("list", list);
        System.out.println(list);


        return "admin/products/list";
    }


//    @PostMapping
//    String pString() {
//
//    }

}
