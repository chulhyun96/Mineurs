package com.newlecmineursprj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {
    
    @GetMapping("pay")
    public String pay(Model model) {

        return "order/pay";
    }
}
