package com.web.mapper;

import lombok.experimental.UtilityClass;
import com.model.Product;
import com.web.dto.ProductResponse;

@UtilityClass
public class DtoMapper {

    public static ProductResponse toProductResponse(Product product) {
        System.out.println("Mapping product: " + product);
        return ProductResponse.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }
}