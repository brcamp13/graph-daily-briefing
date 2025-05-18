package com.bmcapps.graphdailybriefing.service;


import com.bmcapps.graphdailybriefing.client.coinMarketCap.CoinMarketCapFeignClient;
import com.bmcapps.graphdailybriefing.mapper.CoinMarketCapGetQuotesToCryptocurrencySchemaMapper;
import com.bmcapps.graphdailybriefing.model.coinMarketCapApi.CoinMarketCapApiResponse;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {

    private final CoinMarketCapFeignClient coinMarketCapFeignClient;
    private final CoinMarketCapGetQuotesToCryptocurrencySchemaMapper mapper;

    @Autowired
    public CryptoService(CoinMarketCapFeignClient coinMarketCapFeignClient, CoinMarketCapGetQuotesToCryptocurrencySchemaMapper mapper) {
        this.coinMarketCapFeignClient = coinMarketCapFeignClient;
        this.mapper = mapper;
    }

    public List<CryptoSchema> getCryptocurrencies(List<String> slugs) {
        String slugsParam = String.join(",", slugs);
        CoinMarketCapApiResponse response = coinMarketCapFeignClient.getCryptocurrencyQuotes(slugsParam);

        return mapper.mapCoinMarketCapGetQuotesToCryptoSchema(response);
    }
}
