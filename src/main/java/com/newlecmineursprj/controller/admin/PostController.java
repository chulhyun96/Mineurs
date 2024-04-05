package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.entity.Post;
import com.newlecmineursprj.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("admin/post")
@Controller
public class PostController {
    @Autowired
    PostService service;
    @GetMapping
    public String list(Model model) {
        List<Post> list = service.getPostList();
        model.addAttribute("list", list);
        return "admin/post/list";
    }

    @GetMapping("preview")
    public String regForm() {

        return "admin/post/preview";
    }
}
