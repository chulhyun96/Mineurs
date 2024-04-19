package com.newlecmineursprj.entity;

import java.sql.Date;
import java.util.Objects;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long id;
    private String name;
    private int price;
    private String mainImgPath;
    private String description;
    private boolean displayed;
    private boolean sold;
    private String code;
    private boolean deliveryToday;
    private double discountRate;
    private Long categoryId;
    private Date regDate;

    public static Product saveImg(String mainImgPath) {
        return Product.builder()
                .mainImgPath(mainImgPath)
                .build();
    }
}
