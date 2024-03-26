package com.newlecmineursprj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailImg {
    private Long productId;
    private String path;
    private Long id;

}
