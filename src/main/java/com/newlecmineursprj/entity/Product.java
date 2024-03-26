package com.newlecmineursprj.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Setter
@Getter
@Entity
@Data
@Table(name = "product", schema = "mineurs_db")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "reg_date")
    private Date regDate;
    @Basic
    @Column(name = "selling_price")
    private int sellingPrice;
    @Basic
    @Column(name = "supplying_price")
    private int supplyingPrice;
    @Basic
    @Column(name = "img")
    private String img;
    @Basic
    @Column(name = "description")
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
