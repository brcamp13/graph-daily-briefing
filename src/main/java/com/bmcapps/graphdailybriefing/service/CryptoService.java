package com.bmcapps.graphdailybriefing.service;


import com.bmcapps.graphdailybriefing.client.coinMarketCap.CoinMarketCapFeignClient;
import com.bmcapps.graphdailybriefing.mapper.CoinMarketCapGetFearGreedToCryptoMarketSchemaMapper;
import com.bmcapps.graphdailybriefing.mapper.CoinMarketCapGetQuotesToCryptocurrencySchemaMapper;
import com.bmcapps.graphdailybriefing.model.coinMarketCapApi.fearAndGreed.CoinMarketCapFearGreedApiResponse;
import com.bmcapps.graphdailybriefing.model.coinMarketCapApi.quotes.CoinMarketCapQuotesApiResponse;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoMarketDataSchema;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {

    private final CoinMarketCapFeignClient coinMarketCapFeignClient;
    private final CoinMarketCapGetQuotesToCryptocurrencySchemaMapper coinMarketCapGetQuotesToCryptocurrencySchemaMapper;
    private final CoinMarketCapGetFearGreedToCryptoMarketSchemaMapper coinMarketCapGetFearGreedToCryptoMarketSchemaMapper;

    @Autowired
    public CryptoService(CoinMarketCapFeignClient coinMarketCapFeignClient, CoinMarketCapGetQuotesToCryptocurrencySchemaMapper coinMarketCapGetQuotesToCryptocurrencySchemaMapper) {
        this.coinMarketCapFeignClient = coinMarketCapFeignClient;
        this.coinMarketCapGetQuotesToCryptocurrencySchemaMapper = coinMarketCapGetQuotesToCryptocurrencySchemaMapper;
        this.coinMarketCapGetFearGreedToCryptoMarketSchemaMapper = new CoinMarketCapGetFearGreedToCryptoMarketSchemaMapper();
    }

    public List<CryptoSchema> getCryptocurrencies(List<String> slugs) {
        String slugsParam = String.join(",", slugs);
        CoinMarketCapQuotesApiResponse response = coinMarketCapFeignClient.getCryptocurrencyQuotes(slugsParam);

        return coinMarketCapGetQuotesToCryptocurrencySchemaMapper.mapCoinMarketCapGetQuotesToCryptoSchema(response);
    }

    public CryptoMarketDataSchema getCryptoMarketData() {
        CoinMarketCapFearGreedApiResponse response = coinMarketCapFeignClient.getFearAndGreedIndex();
        return coinMarketCapGetFearGreedToCryptoMarketSchemaMapper.mapToCryptoMarketDataSchema(response);
    }
}
