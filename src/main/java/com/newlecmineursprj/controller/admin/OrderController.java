package com.newlecmineursprj.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecmineursprj.entity.OrderView;
import com.newlecmineursprj.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequestMapping("admin/order")
@Controller("adminOrderController")
@RequiredArgsConstructor
public class OrderController {
    private static final String ORDER_VIEW = "/admin/order";

    private final OrderService service;

    @GetMapping("list")
    public String list(Model model, @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(required = false) String searchMethod,
            @RequestParam(defaultValue = "") String searchKeyword) {

        int count = service.getCount(searchMethod, searchKeyword.trim());

        List<OrderView> list = service.getList(page, searchMethod, searchKeyword);
        model.addAttribute("list", list);
        model.addAttribute("count", count);

        return ORDER_VIEW + "/list";
    }

}
