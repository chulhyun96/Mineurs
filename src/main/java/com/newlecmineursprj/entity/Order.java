package com.newlecmineursprj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private long id;
    private Long memberId;
    private Long productId;
    private Date regDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        return id == that.id && Objects.equals(memberId, that.memberId) && Objects.equals(productId, that.productId) && Objects.equals(regDate, that.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, productId, regDate);
    }
}
