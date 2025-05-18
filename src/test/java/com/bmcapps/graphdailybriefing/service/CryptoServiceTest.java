package com.bmcapps.graphdailybriefing.service;

import com.bmcapps.graphdailybriefing.client.coinMarketCap.CoinMarketCapFeignClient;
import com.bmcapps.graphdailybriefing.mapper.CoinMarketCapGetQuotesToCryptocurrencySchemaMapper;
import com.bmcapps.graphdailybriefing.model.coinMarketCapApi.CoinMarketCapApiResponse;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CryptoServiceTest {

    @Mock
    private CoinMarketCapFeignClient coinMarketCapFeignClient;

    @Mock
    private CoinMarketCapGetQuotesToCryptocurrencySchemaMapper mapper;

    @InjectMocks
    private CryptoService cryptoService;

    private List<String> slugs;
    private CoinMarketCapApiResponse apiResponse;
    private List<CryptoSchema> expectedCryptoSchemas;

    @BeforeEach
    void setUp() {
        slugs = Arrays.asList("Bitcoin", "Ethereum");
        apiResponse = new CoinMarketCapApiResponse();

        CryptoSchema bitcoinSchema = new CryptoSchema();
        bitcoinSchema.setName("Bitcoin");
        bitcoinSchema.setSymbol("BTC");
        bitcoinSchema.setPrice(50000.0);
        bitcoinSchema.setPercentChange24h(1.5);
        bitcoinSchema.setPercentChange60d(5.0);
        bitcoinSchema.setPercentChange90d(10.0);
        bitcoinSchema.setMarketCap(1000000000.0);

        CryptoSchema ethereumSchema = new CryptoSchema();
        ethereumSchema.setName("Ethereum");
        ethereumSchema.setSymbol("ETH");
        ethereumSchema.setPrice(4000.0);
        ethereumSchema.setPercentChange24h(0.5);
        ethereumSchema.setMarketCap(500000000.0);

        expectedCryptoSchemas = Arrays.asList(bitcoinSchema, ethereumSchema);
    }

    @Test
    void getCryptocurrencies_ShouldReturnMappedCryptoData() {
        // Arrange
        String slugsParam = String.join(",", slugs);
        when(coinMarketCapFeignClient.getCryptocurrencyQuotes(slugsParam)).thenReturn(apiResponse);
        when(mapper.mapCoinMarketCapGetQuotesToCryptoSchema(apiResponse)).thenReturn(expectedCryptoSchemas);

        // Act
        List<CryptoSchema> result = cryptoService.getCryptocurrencies(slugs);

        // Assert
        assertNotNull(result);
        assertEquals(expectedCryptoSchemas.size(), result.size());
        assertEquals(expectedCryptoSchemas, result);
        verify(coinMarketCapFeignClient).getCryptocurrencyQuotes(slugsParam);
        verify(mapper).mapCoinMarketCapGetQuotesToCryptoSchema(apiResponse);
    }

    @Test
    void getCryptocurrencies_WithEmptySlugs_ShouldReturnEmptyList() {
        // Arrange
        List<String> emptySlugs = Collections.emptyList();
        String slugsParam = ""; // Empty string when slugs list is empty
        CoinMarketCapApiResponse emptyApiResponse = new CoinMarketCapApiResponse(); // Or null, depending on expected behavior
        List<CryptoSchema> emptyExpectedSchemas = Collections.emptyList();

        when(coinMarketCapFeignClient.getCryptocurrencyQuotes(slugsParam)).thenReturn(emptyApiResponse);
        when(mapper.mapCoinMarketCapGetQuotesToCryptoSchema(emptyApiResponse)).thenReturn(emptyExpectedSchemas);

        // Act
        List<CryptoSchema> result = cryptoService.getCryptocurrencies(emptySlugs);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(coinMarketCapFeignClient).getCryptocurrencyQuotes(slugsParam);
        verify(mapper).mapCoinMarketCapGetQuotesToCryptoSchema(emptyApiResponse);
    }
}
