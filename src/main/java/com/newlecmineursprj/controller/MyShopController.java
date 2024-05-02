package com.newlecmineursprj.controller;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.service.*;
import com.newlecmineursprj.util.CustomPageImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("myshop")
@Slf4j
public class MyShopController {

    private final OrderService orderService;
    private final ProductService productService;

    private final CategoryService categoryService;
    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("order/list")
    public String orderList(@RequestParam(value = "p", defaultValue = "1") int pageNumber,
            @RequestParam(value = "s", defaultValue = "6") int pageSize,
            @RequestParam(value = "sm", defaultValue = "ordered_datetime") String sortMethod,
            @RequestParam(value = "sd", defaultValue = "DESC") String sortDirection,
            @RequestParam(defaultValue = "") String searchMethod, @RequestParam(defaultValue = "") String searchKeyword,
            @AuthenticationPrincipal WebUserDetails webUserDetails, Model model) {

        long memberId = webUserDetails.getId();

        log.debug("memberId: {}", memberId);

        model.addAttribute("orderPage", orderService.getList(
                pageNumber, pageSize, sortMethod, sortDirection, 5, searchMethod, searchKeyword, memberId));

        return "myshop/order/list";
    }

    @GetMapping
    public String index() {
        return "myshop/index";
    }

    @GetMapping("wishlist")
    public String wishlist(@RequestParam(value = "p", defaultValue = "1") int pageNumber,
            @RequestParam(value = "s", defaultValue = "12") int pageSize,
            Model model, @AuthenticationPrincipal WebUserDetails webUserDetails) {

        log.info("아무렇게");

        long memberId = webUserDetails.getId();

        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList", categoryList);

        CustomPageImpl<ProductListDTO> productPage = productService.getWishList(pageNumber, pageSize, 5, memberId);
        model.addAttribute("productPage", productPage);

        return "myshop/wishlist";
    }


}
