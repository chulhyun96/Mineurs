package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.entity.Post;
import com.newlecmineursprj.service.PostService;
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
    PostService service;
    @GetMapping
    public String list(Model model) {
        List<Post> list = service.getList();
        model.addAttribute("list", list);
        return "admin/board/list";
    }

    @GetMapping("preview")
    public String regForm() {

        return "admin/board/preview";
    }
}
