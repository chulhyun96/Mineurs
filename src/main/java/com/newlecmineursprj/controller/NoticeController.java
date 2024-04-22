package com.newlecmineursprj.controller;

import com.newlecmineursprj.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("notices")
@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService service;
    @GetMapping
    public String list(Model model){


        model.addAttribute("list", service.findAll());
        return "board/notice/list";

    }


    @GetMapping("{id}")
    public String detail(Model model, @PathVariable Long id){
        model.addAttribute("notice", service.findById(id));
        return "board/notice/detail";
    }
}
