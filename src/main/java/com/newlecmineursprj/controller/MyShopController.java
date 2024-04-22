package com.newlecmineursprj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecmineursprj.service.MemberService;
import com.newlecmineursprj.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("myshop")
public class MyShopController {

    private final OrderService orderService;

    @GetMapping("order/list")
    public String orderList() {
        return "myshop/order/list";
    }

    @GetMapping("index")
    public String index() {
        return "myshop/index";
    }

}
