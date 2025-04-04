package com.lpandza.restapi.service;

import com.lpandza.restapi.dto.ProductDto;
import com.lpandza.restapi.request.ProductRequest;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    void save(ProductRequest productRequest);
}
