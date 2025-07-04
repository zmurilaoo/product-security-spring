package com.product.pip.api;


import com.product.pip.domain.product.Product;
import com.product.pip.domain.product.ProductRequestDto;
import com.product.pip.domain.product.ProductResponseDto;
import com.product.pip.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {


    @Autowired
    private ProductRepository repository;

    @PostMapping()
    public ResponseEntity create(@RequestBody @Valid ProductRequestDto respostaDto) {

        Product product = new Product(respostaDto);

        product = this.repository.save(product);

        return ResponseEntity.ok(product);


    }

}
