package com.product.pip.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto(


        @NotBlank
        String name,


        @NotNull
        Integer price

) {
}
