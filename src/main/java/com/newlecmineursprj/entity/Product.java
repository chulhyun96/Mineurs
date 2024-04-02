package com.newlecmineursprj.entity;

import java.sql.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Date regDate;
    private int sellingPrice;
    private int supplyingPrice;
    private String imgPath;
    private String description;
    private Long categoryId;
}
