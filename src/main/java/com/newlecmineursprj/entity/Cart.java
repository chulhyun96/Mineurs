package com.newlecmineursprj.entity;

import lombok.Data;

@Data
public class Cart {
    Long memberId;
    Long productItemId;
    int qty;
}
