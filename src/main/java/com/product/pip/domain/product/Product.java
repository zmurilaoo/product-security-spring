package com.product.pip.domain.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@Table(name = "products")
@NoArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "price", nullable = false)
    private Integer price;



    public Product (ProductRequestDto dto) {
        this.name = dto.name();
        this.price = dto.price();
    }


}
