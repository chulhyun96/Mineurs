package com.newlecmineursprj.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "mineurs_db", catalog = "")
public class ProductEntity {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getSupplyingPrice() {
        return supplyingPrice;
    }

    public void setSupplyingPrice(int supplyingPrice) {
        this.supplyingPrice = supplyingPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id && sellingPrice == that.sellingPrice && supplyingPrice == that.supplyingPrice && Objects.equals(name, that.name) && Objects.equals(regDate, that.regDate) && Objects.equals(img, that.img) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, regDate, sellingPrice, supplyingPrice, img, description);
    }
}
