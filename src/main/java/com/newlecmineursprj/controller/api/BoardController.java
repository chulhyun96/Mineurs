package com.newlecmineursprj.controller.api;

import com.newlecmineursprj.entity.Board;
import com.newlecmineursprj.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/post")
@RestController("adminRestPostController")
public class BoardController {

    @Autowired
    BoardService service;

    @GetMapping("{id}")
    public Board preview(@PathVariable Long id) {
        return service.getPostById(id);
    }

}
