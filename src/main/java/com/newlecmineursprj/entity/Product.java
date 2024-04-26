package com.newlecmineursprj.entity;

import java.sql.Date;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

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

    public static void saveImg(String mainImgPath, Product newProduct) {
        newProduct.setMainImgPath(mainImgPath);
    }
}
