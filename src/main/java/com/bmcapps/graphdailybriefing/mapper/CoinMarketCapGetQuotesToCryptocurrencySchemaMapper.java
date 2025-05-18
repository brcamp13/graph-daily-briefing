package com.bmcapps.graphdailybriefing.mapper;

import com.bmcapps.graphdailybriefing.model.coinMarketCapApi.CoinMarketCapApiResponse;
import com.bmcapps.graphdailybriefing.model.coinMarketCapApi.CryptoCurrency;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoSchema;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class CoinMarketCapGetQuotesToCryptocurrencySchemaMapper {
    public List<CryptoSchema> mapCoinMarketCapGetQuotesToCryptoSchema(CoinMarketCapApiResponse response) {
        Map<String, CryptoCurrency> apiCryptos = response.getData();

        return apiCryptos.values().stream()
                .map(this::mapCryptoCurrency)
                .collect(Collectors.toList());
    }

    private CryptoSchema mapCryptoCurrency(CryptoCurrency crypto) {
        CryptoSchema result = new CryptoSchema();
        result.setName(crypto.getName());
        result.setSymbol(crypto.getSymbol());

        if (crypto.getQuote() != null && crypto.getQuote().getUsd() != null) {
            result.setPrice(crypto.getQuote().getUsd().getPrice());
            result.setMarketCap(crypto.getQuote().getUsd().getMarketCap());
            result.setPercentChange24h(crypto.getQuote().getUsd().getPercentChange24h());
            result.setPercentChange60d(crypto.getQuote().getUsd().getPercentChange60d());
            result.setPercentChange90d(crypto.getQuote().getUsd().getPercentChange90d());
        }

        return result;
    }
}
