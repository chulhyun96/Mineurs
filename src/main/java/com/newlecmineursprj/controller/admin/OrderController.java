package com.newlecmineursprj.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecmineursprj.entity.Order;
import com.newlecmineursprj.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequestMapping("admin/order")
@Controller("adminOrderController")
@RequiredArgsConstructor
public class OrderController {
    private static final String ORDER_VIEW = "/admin/order";

    private final OrderService service;

    @GetMapping("list")
    public String list(Model model, @RequestParam(defaultValue = "1") Integer page) {
        List<Order> list = service.getList();
        model.addAttribute("list", list);

        return ORDER_VIEW + "/list";
    }

}
