package com.newlecmineursprj.entity;

import java.sql.Date;

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

    public static void saveNewImg(String mainImgPath, Product newProduct) {
        newProduct.setMainImgPath(mainImgPath);
    }
    public String getCurrentImg(String mainImgPath) {
        return mainImgPath;
    }
}
