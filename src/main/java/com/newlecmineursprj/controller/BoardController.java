package com.newlecmineursprj.controller;

import com.newlecmineursprj.entity.QnaView;
import com.newlecmineursprj.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("qna")
public class BoardController {

    private final BoardService service;

    @GetMapping
    public String qnaList(@RequestParam(required = false) String searchMethod
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "") String searchKeyword
            , @RequestParam(defaultValue = "0") Integer categoryId
            , Model model) {

        List<QnaView> list = service.getList(page, searchMethod, searchKeyword, categoryId,null);
        model.addAttribute("list", list);
        return "/board/qna/list";
    }

    @GetMapping("reg")
    public String regForm() {

        return "board/qna/new";
    }

    @PostMapping("reg")
    public String reg() {


        return "redirect:/reg";
    }
    @GetMapping("{id}")
    public String detail(Model model, @PathVariable Long id){


        model.addAttribute("qna", service.getById(id));

        return "board/qna/detail";
    }
}
