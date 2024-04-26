package com.newlecmineursprj.entity;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private int price;

    @NotBlank
    private String description;

    private String mainImgPath;
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
