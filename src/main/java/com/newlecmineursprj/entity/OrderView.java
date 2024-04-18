package com.newlecmineursprj.entity;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderView {
    private String code;
    private Date orderedDatetime;
    private String userName;
    private List<String> productName;
    private int totalProductPrice;
    private int totalPaymentAmount;
    private String paymentMethod;
    private String orderState;

}
