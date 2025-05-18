package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoMarketDataSchema;
import com.bmcapps.graphdailybriefing.service.CryptoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CryptoMarketDataFetcherTest {

    @Mock
    private CryptoService cryptoService;

    @InjectMocks
    private CryptoMarketDataFetcher cryptoMarketDataFetcher;

    @Test
    void getCryptoMarketData_ShouldReturnCryptoMarketData() {
        // Arrange
        CryptoMarketDataSchema expectedMarketData = new CryptoMarketDataSchema();
        expectedMarketData.setFearAndGreedIndexValue(55);
        expectedMarketData.setFearAndGreedIndexValueClassification("Neutral");

        when(cryptoService.getCryptoMarketData()).thenReturn(expectedMarketData);

        // Act
        CryptoMarketDataSchema result = cryptoMarketDataFetcher.getCryptoMarketData();

        // Assert
        assertNotNull(result);
        assertEquals(expectedMarketData, result);
    }
}