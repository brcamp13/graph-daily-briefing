package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.CryptoCurrency;
import com.bmcapps.graphdailybriefing.CryptoRequest;
import com.bmcapps.graphdailybriefing.CryptoResponse;
import com.bmcapps.graphdailybriefing.CryptoServiceGrpc;
import com.bmcapps.graphdailybriefing.mapper.CryptoMsResponseToCryptocurrencySchemaMapper;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoSchema;
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
    private CryptoServiceGrpc.CryptoServiceBlockingStub cryptoStub;

    @Mock
    private CryptoMsResponseToCryptocurrencySchemaMapper mapper;

    private CryptoDataFetcher cryptoDataFetcher;

    @BeforeEach
    void setUp() {
        cryptoDataFetcher = new CryptoDataFetcher(cryptoStub, mapper);
    }

    @Test
    void getCryptocurrencies_ShouldReturnListOfCryptocurrencies() {
        // Arrange
        List<String> slugs = Arrays.asList("bitcoin", "ethereum");

        CryptoRequest request = CryptoRequest.newBuilder().addAllSlugs(slugs).build();

        CryptoCurrency bitcoinResponse = CryptoCurrency.newBuilder()
                .setName("Bitcoin")
                .setSymbol("BTC")
                .setPrice(50000.0)
                .setPercentChange24H(1.5)
                .setPercentChange60D(5.0)
                .setPercentChange90D(10.0)
                .setMarketCap(1000000000.0)
                .build();

        CryptoCurrency ethereumResponse = CryptoCurrency.newBuilder()
                .setName("Ethereum")
                .setSymbol("ETH")
                .setPrice(4000.0)
                .setPercentChange24H(0.5)
                .setMarketCap(500000000.0)
                .build();

        CryptoResponse response = CryptoResponse.newBuilder()
                .addCurrencies(bitcoinResponse)
                .addCurrencies(ethereumResponse)
                .build();

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

        when(cryptoStub.getCryptoCurrencies(request)).thenReturn(response);
        when(mapper.mapCryptoMsResponseToCryptoSchema(bitcoinResponse)).thenReturn(bitcoinSchema);
        when(mapper.mapCryptoMsResponseToCryptoSchema(ethereumResponse)).thenReturn(ethereumSchema);

        // Act
        List<CryptoSchema> result = cryptoDataFetcher.getCryptocurrencies(slugs);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(bitcoinSchema, result.get(0));
        assertEquals(ethereumSchema, result.get(1));
        verify(cryptoStub).getCryptoCurrencies(request);
        verify(mapper).mapCryptoMsResponseToCryptoSchema(bitcoinResponse);
        verify(mapper).mapCryptoMsResponseToCryptoSchema(ethereumResponse);
    }
}
