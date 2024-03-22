package com.newlecmineursprj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CategoryEntity {

    private Long id;
    private String name;
}