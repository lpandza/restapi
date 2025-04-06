package com.lpandza.restapi.controller;

import com.lpandza.restapi.dto.PopularProduct;
import com.lpandza.restapi.dto.ProductDto;
import com.lpandza.restapi.request.ProductFilterRequest;
import com.lpandza.restapi.request.ProductRequest;
import com.lpandza.restapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(ProductFilterRequest productFilterRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAll(productFilterRequest));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Validated ProductRequest productRequest) {
        productService.save(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/popular")
    public ResponseEntity<List<PopularProduct>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getTopProductsByRating());
    }
}
