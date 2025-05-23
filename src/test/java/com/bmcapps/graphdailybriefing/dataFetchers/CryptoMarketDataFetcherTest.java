package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.CryptoServiceGrpc;
import com.bmcapps.graphdailybriefing.MarketDataRequest;
import com.bmcapps.graphdailybriefing.MarketDataResponse;
import com.bmcapps.graphdailybriefing.mapper.CryptoMsResponseToCryptocurrencySchemaMapper;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoMarketDataSchema;
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
    private CryptoServiceGrpc.CryptoServiceBlockingStub cryptoServiceBlockingStub;

    @Mock
    private CryptoMsResponseToCryptocurrencySchemaMapper cryptoMsResponseToCryptocurrencySchemaMapper;

    @InjectMocks
    private CryptoMarketDataFetcher cryptoMarketDataFetcher;

    @Test
    void getCryptoMarketData_ShouldReturnCryptoMarketData() {
        // Arrange
        MarketDataRequest request = MarketDataRequest.newBuilder().build();
        MarketDataResponse grpcResponse = MarketDataResponse.newBuilder()
                .setFearAndGreedIndexValue(55)
                .setFearAndGreedIndexValueClassification("Neutral")
                .build();

        CryptoMarketDataSchema expectedMarketData = new CryptoMarketDataSchema();
        expectedMarketData.setFearAndGreedIndexValue(55);
        expectedMarketData.setFearAndGreedIndexValueClassification("Neutral");

        when(cryptoServiceBlockingStub.getCryptoMarketData(request)).thenReturn(grpcResponse);
        when(cryptoMsResponseToCryptocurrencySchemaMapper.mapCryptoMsMarketDataToCryptoMarketDataSchema(grpcResponse))
                .thenReturn(expectedMarketData);

        // Act
        CryptoMarketDataSchema result = cryptoMarketDataFetcher.getCryptoMarketData();

        // Assert
        assertNotNull(result);
        assertEquals(expectedMarketData, result);
    }
}