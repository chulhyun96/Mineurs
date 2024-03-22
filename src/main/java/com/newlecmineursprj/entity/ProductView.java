package com.newlecmineursprj.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class ProductView {
    private Long id;
    private String name;
    private Date regDate;
    private int sellingPrice;
    private int supplyingPrice;
    private String img;
    private String description;
    //category_id 지움
    private String categoryName;
    // private List<String> path;
}
