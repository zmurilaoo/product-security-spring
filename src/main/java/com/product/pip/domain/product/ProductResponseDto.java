package com.product.pip.domain.product;

public record ProductResponseDto(String name, Integer price) {

    public ProductResponseDto(Product product){
        this(product.getName(), product.getPrice());
    }
}
