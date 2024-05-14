package com.newlecmineursprj.controller.api;

import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/colors")
@RequiredArgsConstructor
@RestController("apiColorController")
public class ColorController {

    private final ColorService service;

    @GetMapping
    public ResponseEntity<List<Color>> list(@RequestParam(value="kor-name") String query){
        return ResponseEntity.ok(service.getListByKorName(query));
    }
}
