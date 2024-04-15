package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.entity.Board;
import com.newlecmineursprj.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("admin/board")
@Controller
public class BoardController {
    @Autowired
    BoardService service;
    @GetMapping
    public String list(Model model) {
        List<Board> list = service.getPostList();
        model.addAttribute("list", list);
        return "admin/post/list";
    }

    @GetMapping("preview")
    public String regForm() {

        return "admin/post/preview";
    }
}
