package com.bmcapps.graphdailybriefing.mapper;

import com.bmcapps.graphdailybriefing.CryptoCurrency;
import com.bmcapps.graphdailybriefing.MarketDataResponse;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoMarketDataSchema;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CryptoMsResponseToCryptocurrencySchemaMapperTest {

    private final CryptoMsResponseToCryptocurrencySchemaMapper mapper = new CryptoMsResponseToCryptocurrencySchemaMapper();

    @Test
    void testMapCryptoMsResponseToCryptoSchema() {
        CryptoCurrency currency = CryptoCurrency.newBuilder()
                .setName("Bitcoin")
                .setSymbol("BTC")
                .setPrice(50000.0)
                .setMarketCap(1000000000.0)
                .setPercentChange24H(2.5)
                .setPercentChange60D(10.0)
                .setPercentChange90D(15.0)
                .build();

        CryptoSchema result = mapper.mapCryptoMsResponseToCryptoSchema(currency);

        assertNotNull(result);
        assertEquals("Bitcoin", result.getName());
        assertEquals("BTC", result.getSymbol());
        assertEquals(50000.0, result.getPrice());
        assertEquals(1000000000.0, result.getMarketCap());
        assertEquals(2.5, result.getPercentChange24h());
        assertEquals(10.0, result.getPercentChange60d());
        assertEquals(15.0, result.getPercentChange90d());
    }

    @Test
    void testMapCryptoMsMarketDataToCryptoMarketDataSchema() {
        MarketDataResponse marketDataResponse = MarketDataResponse.newBuilder()
                .setFearAndGreedIndexValue(50)
                .setFearAndGreedIndexValueClassification("Neutral")
                .build();

        CryptoMarketDataSchema result = mapper.mapCryptoMsMarketDataToCryptoMarketDataSchema(marketDataResponse);

        assertNotNull(result);
        assertEquals(50, result.getFearAndGreedIndexValue());
        assertEquals("Neutral", result.getFearAndGreedIndexValueClassification());
    }
}
