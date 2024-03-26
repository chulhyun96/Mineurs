package com.newlecmineursprj.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<DetailImg> detailImgs;
}
