package com.lpandza.restapi.service.impl;

import com.lpandza.restapi.dto.ProductDto;
import com.lpandza.restapi.model.Product;
import com.lpandza.restapi.repository.ProductRepository;
import com.lpandza.restapi.request.ProductRequest;
import com.lpandza.restapi.service.HnbApiService;
import com.lpandza.restapi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final HnbApiService hnbApiService;

    public ProductServiceImpl(ProductRepository productRepository, HnbApiService hnbApiService) {
        this.productRepository = productRepository;
        this.hnbApiService = hnbApiService;
    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll()
                                .stream()
                                .map(product -> new ProductDto(
                                        product.getCode(),
                                        product.getName(),
                                        product.getPriceEur(),
                                        product.getPriceUsd(),
                                        product.getDescription()
                                ))
                                .toList();
    }

    @Override
    public void save(ProductRequest productRequest) {
        log.info("Saving new product {} ", productRequest);

        validateProductDoesNotExist(productRequest);

        BigDecimal exchangeRateUsd = new BigDecimal(hnbApiService.getExchangeRate().rate());
        BigDecimal priceUsd = productRequest.priceEur().multiply(exchangeRateUsd);

        productRepository.save(
                new Product(
                        productRequest.code(),
                        productRequest.name(),
                        productRequest.priceEur(),
                        priceUsd,
                        productRequest.description()
                ));

        log.info("Product saved successfully");
    }

    private void validateProductDoesNotExist(ProductRequest productRequest) {
        productRepository.findByCode(productRequest.code())
                         .ifPresent(product -> {
                             log.error("Product with code {} already exists", productRequest.code());
                             throw new RuntimeException(
                                     "Product with code " + productRequest.code() + " already exists");
                         });
    }

}
