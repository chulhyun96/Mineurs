package com.newlecmineursprj.entity;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductView {
    private long id;
    private String name;
    private int price;
    private List<Color> colors;
    private List<Size> sizes;
    private boolean isDeliveryToday;
    private Boolean isSold;
    private String mainImgPath;
    private Date regDate;
    private Boolean currentUserLiked;
    private Long likeCount;

    private Double discountRate;


}
