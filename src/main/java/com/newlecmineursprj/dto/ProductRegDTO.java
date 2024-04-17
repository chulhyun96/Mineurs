package com.newlecmineursprj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRegDTO {
    private Long id;
    private String name;
    private Date regDate;
    private int price;
    private MultipartFile mainImg;
    private String description;
    private int isDisplayed;
    private int isSold;
    private int isDeliveryToday;
    private String code;
    private double discountRate;
    private Long categoryId;
}
