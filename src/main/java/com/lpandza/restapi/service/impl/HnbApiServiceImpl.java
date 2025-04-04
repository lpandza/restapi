package com.lpandza.restapi.service.impl;

import com.lpandza.restapi.dto.ExchangeRateResponse;
import com.lpandza.restapi.service.HnbApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Slf4j
@Service
public class HnbApiServiceImpl implements HnbApiService {
    private static final String EXCHANGE_PATH = "tecajn-eur";
    private static final String VERSION = "v3";
    private static final String CURRENCY_PARAM = "valuta";
    private static final String DEFAULT_CURRENCY = "USD";


    private final RestClient restClient;

    private String baseUrl;

    public HnbApiServiceImpl(@Value("${external-api.hnb-url}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public ExchangeRateResponse getExchangeRate() {
        try {
            List<ExchangeRateResponse> responseList =
                    restClient.get()
                              .uri(uriBuilder -> uriBuilder
                                      .pathSegment(EXCHANGE_PATH, VERSION)
                                      .queryParam(CURRENCY_PARAM, DEFAULT_CURRENCY)
                                      .build())
                              .accept(MediaType.APPLICATION_JSON)
                              .retrieve()
                              .body(new ParameterizedTypeReference<List<ExchangeRateResponse>>() {
                              });

            if (responseList == null || responseList.isEmpty()) {
                log.warn("Exchange rate response list is null or empty");
                throw new RestClientException("Exchange rate not found in response");
            }

            return responseList.stream().findFirst().get();

        } catch (RestClientException e) {
            log.error("Error fetching exchange rate from HNB API: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch exchange rate: " + e.getMessage(), e);
        }
    }

}
