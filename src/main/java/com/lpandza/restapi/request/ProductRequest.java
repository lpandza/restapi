package com.lpandza.restapi.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank @Size(min = 15, max = 15)
        String code,

        @NotBlank
        String name,

        @NotNull @Positive
        BigDecimal priceEur,

        @NotNull
        String description
) {
}
