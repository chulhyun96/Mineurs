package com.newlecmineursprj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
    private Date regDate;
    private int sellingPrice;
    private int supplyingPrice;
    private String img;
    private String description;

    private Long categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product that = (Product) o;
        return id == that.id && sellingPrice == that.sellingPrice && supplyingPrice == that.supplyingPrice
                && Objects.equals(name, that.name) && Objects.equals(regDate, that.regDate)
                && Objects.equals(img, that.img) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, regDate, sellingPrice, supplyingPrice, img, description);
    }
}
