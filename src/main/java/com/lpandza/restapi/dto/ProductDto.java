package com.lpandza.restapi.dto;

import java.math.BigDecimal;

public record ProductDto(
        String code,
        String name,
        BigDecimal priceEur,
        BigDecimal priceUsd,
        String description
) {
}
