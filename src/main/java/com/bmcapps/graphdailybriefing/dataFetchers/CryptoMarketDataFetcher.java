package com.bmcapps.graphdailybriefing.dataFetchers;


import com.bmcapps.graphdailybriefing.CryptoServiceGrpc;
import com.bmcapps.graphdailybriefing.MarketDataRequest;
import com.bmcapps.graphdailybriefing.MarketDataResponse;
import com.bmcapps.graphdailybriefing.mapper.CryptoMsResponseToCryptocurrencySchemaMapper;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoMarketDataSchema;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;


@DgsComponent
public class CryptoMarketDataFetcher {
    private final CryptoServiceGrpc.CryptoServiceBlockingStub cryptoServiceBlockingStub;
    private final CryptoMsResponseToCryptocurrencySchemaMapper cryptoMsResponseToCryptocurrencySchemaMapper;

    @Autowired
    public CryptoMarketDataFetcher(CryptoServiceGrpc.CryptoServiceBlockingStub cryptoServiceBlockingStub,
                                   CryptoMsResponseToCryptocurrencySchemaMapper cryptoMsResponseToCryptocurrencySchemaMapper) {
        this.cryptoServiceBlockingStub = cryptoServiceBlockingStub;
        this.cryptoMsResponseToCryptocurrencySchemaMapper = cryptoMsResponseToCryptocurrencySchemaMapper;
    }

    @DgsQuery(field = "cryptoMarketData")
    public CryptoMarketDataSchema getCryptoMarketData() {
        MarketDataRequest request = MarketDataRequest.newBuilder().build();

        MarketDataResponse response = cryptoServiceBlockingStub.getCryptoMarketData(request);

        return cryptoMsResponseToCryptocurrencySchemaMapper.mapCryptoMsMarketDataToCryptoMarketDataSchema(response);
    }

}
