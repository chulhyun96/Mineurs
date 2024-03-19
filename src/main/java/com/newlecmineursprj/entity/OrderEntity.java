package com.newlecmineursprj.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "order", schema = "mineurs_db", catalog = "")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "member_id")
    private Long memberId;
    @Basic
    @Column(name = "product_id")
    private Long productId;
    @Basic
    @Column(name = "reg_date")
    private Date regDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id && Objects.equals(memberId, that.memberId) && Objects.equals(productId, that.productId) && Objects.equals(regDate, that.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, productId, regDate);
    }
}
