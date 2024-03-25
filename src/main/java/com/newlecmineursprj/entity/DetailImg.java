package com.newlecmineursprj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "detail_img", schema = "mineurs_db")
public class DetailImg {
    @Id
    private Long id;
    private String path;
    private Long productId;
}
