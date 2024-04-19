package com.newlecmineursprj.entity;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSubImg {
    private Long productId;
    private String path;
    private Long id;

    public static List<ProductSubImg> saveSubImgs(List<String> storageSubImgName,Product newProduct) {
        return storageSubImgName.stream()
                .map(imgName -> ProductSubImg.builder()
                        .path(imgName)
                        .productId(newProduct.getId())
                        .build())
                .toList();
    }
}
