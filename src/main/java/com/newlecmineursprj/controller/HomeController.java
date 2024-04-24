package com.newlecmineursprj.controller;

import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.service.CategoryService;
import com.newlecmineursprj.service.MemberService;
import com.newlecmineursprj.service.ProductService;
import com.newlecmineursprj.util.CustomPageImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

private final ProductService service;
private final CategoryService categoryService;
private final MemberService memberService;

    @GetMapping
    public String index(@RequestParam(value= "p", defaultValue = "1")int pageNumber
            , @RequestParam(value = "s", defaultValue = "12")int pageSize
            , @RequestParam(value = "sm", defaultValue = "reg_date")String sortMethod
            , @RequestParam(value = "sd", defaultValue = "DESC")String sortDirection
            , @RequestParam(defaultValue = "") String searchMethod
            , @RequestParam(defaultValue = "") String searchKeyword
            , @RequestParam(defaultValue = "0") Long categoryId
            , Model model) {

        CustomPageImpl<ProductListDTO> productPage = service.getList(pageNumber, pageSize, sortMethod, sortDirection, 5, searchMethod, searchKeyword, categoryId);
        List<Category> categoryList = categoryService.getList();

        model.addAttribute("productPage", productPage);
        model.addAttribute("categoryList",categoryList);
        return "list";
    }

    @PostMapping("signup")
    public String signupPost(Member member){
        memberService.save(member);
        log.debug("member: {}", member);
        
        return "redirect:";
    }

    @GetMapping("checkout")
    public String checkout(){
        return "checkout";
    }

    @GetMapping("signin")
    public String signin(){return "signin";}
}
