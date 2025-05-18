package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoSchema;
import com.bmcapps.graphdailybriefing.service.CryptoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CryptoDataFetcherTest {

    @Mock
    private CryptoService cryptoService;

    private CryptoDataFetcher cryptoDataFetcher;

    @BeforeEach
    void setUp() {
        cryptoDataFetcher = new CryptoDataFetcher(cryptoService);
    }

    @Test
    void getCryptocurrencies_ShouldReturnListOfCryptocurrencies() {
        // Arrange
        List<String> slugs = Arrays.asList("bitcoin", "ethereum");

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

        List<CryptoSchema> expectedCryptos = Arrays.asList(
                bitcoinSchema,
                ethereumSchema
        );

        when(cryptoService.getCryptocurrencies(slugs)).thenReturn(expectedCryptos);

        // Act
        List<CryptoSchema> result = cryptoDataFetcher.getCryptocurrencies(slugs);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedCryptos, result);
        verify(cryptoService).getCryptocurrencies(slugs);
    }
}
