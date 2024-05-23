package com.newlecmineursprj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderView {
    private Long id;
    private String code;
    private Date orderedDatetime;
    private String userName;
    private List<String> productNames;
    private int totalProductPrice;
    private int totalDiscountAmount;
    private int totalDeliveryFee;
    private String paymentMethod;
    private String orderState;
    private int productsCount;
    private String pImgPath;

}
