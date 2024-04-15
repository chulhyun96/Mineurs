package com.newlecmineursprj.mapper;

import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.ProductView;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static ProductListDTO toDto(ProductView productView) {

        return ProductListDTO.builder()
                .id(productView.getId())
                .name(productView.getName())
                .price(productView.getPrice())
                .discountPrice((int) (productView.getDiscountRate() * productView.getPrice()))
                .colors(productView.getColors())
                .regDate(productView.getRegDate())
                .sizes(productView.getSizes())
                .isDeliveryToday(productView.isDeliveryToday())
                .isSold(productView.getIsSold())
                .img(productView.getImg())
                .currentUserLiked(productView.getCurrentUserLiked())
                .build();
    }

}