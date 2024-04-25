package com.newlecmineursprj.controller;

import com.newlecmineursprj.config.security.WebUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecmineursprj.service.MemberService;
import com.newlecmineursprj.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("myshop")
@Slf4j
public class MyShopController {

    private final OrderService orderService;

    @GetMapping("order/list")
    public String orderList(@RequestParam(value= "p", defaultValue = "1")int pageNumber
            , @RequestParam(value = "s", defaultValue = "6")int pageSize
            , @RequestParam(value = "sm", defaultValue = "ordered_datetime")String sortMethod
            , @RequestParam(value = "sd", defaultValue = "DESC")String sortDirection
            , @RequestParam(defaultValue = "") String searchMethod
            , @RequestParam(defaultValue = "") String searchKeyword
            , @AuthenticationPrincipal WebUserDetails webUserDetails
            , Model model) {

        long memberId = webUserDetails.getId();

        log.debug("memberId: {}", memberId);

        model.addAttribute("orderPage", orderService.getList(
                pageNumber
                , pageSize
                , sortMethod
                , sortDirection
                , 5
                , searchMethod
                , searchKeyword
                , memberId
                )
        );

        return "myshop/order/list";
    }

    @GetMapping
    public String index() {
        return "myshop/index";
    }

}
