package com.newlecmineursprj.entity;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductView {
    private Long id;
    private String name;
    private Date regDate;
    private int sellingPrice;
    private int supplyingPrice;
    private String img;
    private String description;
    private String categoryName;
    private List<ProductSubImg> productSubImgs;

    public ProductView update(ProductView updateProductView) {
        this.id = updateProductView.getId();
        this.name = updateProductView.getName();
        this.sellingPrice = updateProductView.getSellingPrice();
        this.supplyingPrice = updateProductView.getSupplyingPrice();
        this.img = updateProductView.getImg();
        this.description = updateProductView.getDescription();
        this.categoryName = updateProductView.getCategoryName();
        this.productSubImgs = updateProductView.getProductSubImgs();
        return this;
    }
}
