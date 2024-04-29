package com.newlecmineursprj.dto;

import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductListDTO {
    private long id;
    private String name;
    private int price;
    private int discountPrice;
    private List<Color> colors;
    private List<Size> sizes;
    private boolean isDeliveryToday;
    private Boolean isSold;
    private Date regDate;
    private String mainImgPath;
    private Boolean currentUserLiked;
    private Long likeCount;
}
