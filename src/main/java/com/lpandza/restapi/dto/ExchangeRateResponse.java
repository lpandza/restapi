package com.lpandza.restapi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ExchangeRateResponse(
        @JsonProperty("kupovni_tecaj") String rate
) {
    @JsonCreator
    public ExchangeRateResponse(
            @JsonProperty("valuta") String currency,
            @JsonProperty("kupovni_tecaj") String rate
    ) {
        this(rate.replace(",", "."));
    }
}
