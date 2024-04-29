package com.newlecmineursprj.entity;

import lombok.Data;

@Data
public class Cart {
    Long id;
    Long memberId;
    Long productItemId;
    int qty;
}
