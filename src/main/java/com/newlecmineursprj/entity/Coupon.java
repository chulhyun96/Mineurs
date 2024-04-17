package com.newlecmineursprj.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Coupon {
    private long id;
    private String name;
    private String description;
    private double discountRate; 
    private Date validDateStart;
    private Date validDateEnd;
    private int minimumPurchase;
}
