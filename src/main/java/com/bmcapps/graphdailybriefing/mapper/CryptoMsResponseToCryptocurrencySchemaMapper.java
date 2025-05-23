package com.bmcapps.graphdailybriefing.mapper;

import com.bmcapps.graphdailybriefing.CryptoCurrency;
import com.bmcapps.graphdailybriefing.MarketDataResponse;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoMarketDataSchema;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoSchema;
import org.springframework.stereotype.Component;

@Component
public class CryptoMsResponseToCryptocurrencySchemaMapper {

    public CryptoSchema mapCryptoMsResponseToCryptoSchema(CryptoCurrency currency) {
        CryptoSchema cryptoSchema = new CryptoSchema();
        cryptoSchema.setName(currency.getName());
        cryptoSchema.setSymbol(currency.getSymbol());
        cryptoSchema.setPrice(currency.getPrice());
        cryptoSchema.setMarketCap(currency.getMarketCap());
        cryptoSchema.setPercentChange24h(currency.getPercentChange24H());
        cryptoSchema.setPercentChange60d(currency.getPercentChange60D());
        cryptoSchema.setPercentChange90d(currency.getPercentChange90D());
        return cryptoSchema;
    }

    public CryptoMarketDataSchema mapCryptoMsMarketDataToCryptoMarketDataSchema(MarketDataResponse marketDataResponse) {
        CryptoMarketDataSchema cryptoMarketDataSchema = new CryptoMarketDataSchema();
        cryptoMarketDataSchema.setFearAndGreedIndexValue(marketDataResponse.getFearAndGreedIndexValue());
        cryptoMarketDataSchema.setFearAndGreedIndexValueClassification(marketDataResponse.getFearAndGreedIndexValueClassification());
        return cryptoMarketDataSchema;
    }
}
