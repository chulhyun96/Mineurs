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
    private Date regDate;
    private int price;
    private String imgPath;
    private String description;
    private boolean isDisplayed;
    private boolean isSold;
    private String code;
    private boolean isDeliveryToday;
    private double discountRate;
    private Long categoryId;
}
