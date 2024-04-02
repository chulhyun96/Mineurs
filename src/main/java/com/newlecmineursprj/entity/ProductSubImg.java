package com.newlecmineursprj.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSubImg {
    private Long productId;
    private String path;
    private Long id;
}
