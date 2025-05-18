package com.bmcapps.graphdailybriefing.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import static com.bmcapps.graphdailybriefing.client.coinMarketCap.CoinMarketCapConstants.HEADER_AUTH;


public class CoinMarketCapFeignConfig {

    @Value("${coinmarketcap.api.key}")
    private String apiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(HEADER_AUTH, apiKey);
        };
    }
}
