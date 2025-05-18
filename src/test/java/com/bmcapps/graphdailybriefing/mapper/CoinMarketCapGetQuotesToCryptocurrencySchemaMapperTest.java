package com.bmcapps.graphdailybriefing.mapper;

import com.bmcapps.graphdailybriefing.model.coinMarketCapApi.quotes.CoinMarketCapQuotesApiResponse;
import com.bmcapps.graphdailybriefing.model.coinMarketCapApi.quotes.CryptoCurrency;
import com.bmcapps.graphdailybriefing.model.coinMarketCapApi.quotes.Quote;
import com.bmcapps.graphdailybriefing.model.coinMarketCapApi.quotes.UsdData;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoinMarketCapGetQuotesToCryptocurrencySchemaMapperTest {

    private CoinMarketCapGetQuotesToCryptocurrencySchemaMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CoinMarketCapGetQuotesToCryptocurrencySchemaMapper();
    }

    @Test
    void map_WithFullUsdQuote_ShouldPopulateAllFields() {
        // Arrange
        CoinMarketCapQuotesApiResponse response = new CoinMarketCapQuotesApiResponse();
        CryptoCurrency crypto = new CryptoCurrency();
        crypto.setName("Bitcoin");
        crypto.setSymbol("BTC");
        Quote quote = new Quote();
        UsdData usdData = new UsdData();
        usdData.setPrice(60000.0);
        usdData.setMarketCap(1_000_000_000.0);
        usdData.setPercentChange24h(5.5);
        usdData.setPercentChange60d(20.0);
        usdData.setPercentChange90d(30.0);
        quote.setUsd(usdData);
        crypto.setQuote(quote);
        response.setData(new HashMap<>() {{ put("1", crypto); }});

        // Act
        List<CryptoSchema> result = mapper.mapCoinMarketCapGetQuotesToCryptoSchema(response);

        // Assert
        assertEquals(1, result.size());
        CryptoSchema schema = result.get(0);
        assertEquals("Bitcoin", schema.getName());
        assertEquals("BTC", schema.getSymbol());
        assertEquals(60000.0, schema.getPrice(), 1e-6);
        assertEquals(1_000_000_000.0, schema.getMarketCap(), 1e-6);
        assertEquals(5.5, schema.getPercentChange24h(), 1e-6);
        assertEquals(20.0, schema.getPercentChange60d(), 1e-6);
        assertEquals(30.0, schema.getPercentChange90d(), 1e-6);
    }

    @Test
    void map_WithoutQuote_ShouldDefaultNumericFieldsToZero() {
        // Arrange
        CoinMarketCapQuotesApiResponse response = new CoinMarketCapQuotesApiResponse();
        CryptoCurrency crypto = new CryptoCurrency();
        crypto.setName("Ethereum");
        crypto.setSymbol("ETH");
        crypto.setQuote(null);
        response.setData(new HashMap<>() {{ put("1027", crypto); }});

        // Act
        List<CryptoSchema> result = mapper.mapCoinMarketCapGetQuotesToCryptoSchema(response);

        // Assert
        assertEquals(1, result.size());
        CryptoSchema schema = result.get(0);
        assertEquals("Ethereum", schema.getName());
        assertEquals("ETH", schema.getSymbol());
        assertEquals(0.0, schema.getPrice(), 1e-6);
        assertEquals(0.0, schema.getMarketCap(), 1e-6);
        assertEquals(0.0, schema.getPercentChange24h(), 1e-6);
        assertEquals(0.0, schema.getPercentChange60d(), 1e-6);
        assertEquals(0.0, schema.getPercentChange90d(), 1e-6);
    }

    @Test
    void map_WithEmptyData_ShouldReturnEmptyList() {
        // Arrange
        CoinMarketCapQuotesApiResponse response = new CoinMarketCapQuotesApiResponse();
        response.setData(new HashMap<>());

        // Act
        List<CryptoSchema> result = mapper.mapCoinMarketCapGetQuotesToCryptoSchema(response);

        // Assert
        assertTrue(result.isEmpty());
    }
}