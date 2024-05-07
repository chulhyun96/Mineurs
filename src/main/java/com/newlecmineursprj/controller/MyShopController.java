package com.newlecmineursprj.controller;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.*;
import com.newlecmineursprj.service.*;
import com.newlecmineursprj.util.CustomPageImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.service.CategoryService;
import com.newlecmineursprj.service.CouponService;
import com.newlecmineursprj.service.MemberService;
import com.newlecmineursprj.service.OrderService;
import com.newlecmineursprj.service.PostService;
import com.newlecmineursprj.service.ProductService;
import com.newlecmineursprj.util.CustomPageImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("myshop")
@Slf4j
public class MyShopController {

    private final OrderService orderService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final MemberService memberService;
    // private final PostService postService;

    private final CouponService couponService;

    @GetMapping("order/list")
    public String orderList(@RequestParam(value = "p", defaultValue = "1") int pageNumber,
            @RequestParam(value = "s", defaultValue = "6") int pageSize,
            @RequestParam(value = "sm", defaultValue = "ordered_datetime") String sortMethod,
            @RequestParam(value = "sd", defaultValue = "DESC") String sortDirection,
            @RequestParam(defaultValue = "") String searchMethod, @RequestParam(defaultValue = "") String searchKeyword,
            @AuthenticationPrincipal WebUserDetails webUserDetails, Model model) {

        long memberId = webUserDetails.getId();


        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList", categoryList);

        List<OrderView> list = orderService.getList(pageNumber, searchMethod, searchKeyword);

        model.addAttribute("orderPage",list);
        return "myshop/order/list";
    }

    @GetMapping
    public String index(Model model) {

        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList", categoryList);

        return "myshop/index";
    }

    @GetMapping("wishlist")
    public String wishlist(@RequestParam(value = "p", defaultValue = "1") int pageNumber,
            @RequestParam(value = "s", defaultValue = "12") int pageSize,
            Model model, @AuthenticationPrincipal WebUserDetails webUserDetails) {

        long memberId = webUserDetails.getId();

        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList", categoryList);

        CustomPageImpl<ProductListDTO> productPage = productService.getWishList(pageNumber, pageSize, 5, memberId);
        model.addAttribute("productPage", productPage);

        return "myshop/wishlist";
    }

    @GetMapping("point")
    public String point(Model model, @AuthenticationPrincipal WebUserDetails webUserDetails) {
        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList", categoryList);

        long memberId = webUserDetails.getId();
        Member member = memberService.getById(memberId);

        model.addAttribute("member", member);
        return "myshop/point";
    }

    @GetMapping("coupon")
    public String coupon(Model model, @AuthenticationPrincipal WebUserDetails webUserDetails) {

        long memberId = webUserDetails.getId();

        List<Coupon> coupons = couponService.getValidByMemberId(memberId);

        model.addAttribute("coupons", coupons);
        return "myshop/coupon";
    }

}
