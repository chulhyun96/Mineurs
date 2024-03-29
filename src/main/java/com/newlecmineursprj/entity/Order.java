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
}
