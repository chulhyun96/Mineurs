package com.newlecmineursprj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private long id;
    private Long memberId;
    private Long productId;
    private String code;
    private Date orderedDatetime;
    private int totalProductPrice;
    private int totalDeliveryFee;
    private int totalDiscountAmount;
    private Long deliveryInfoId;
    private Long couponId;
    private Long paymentMethodId;
}
