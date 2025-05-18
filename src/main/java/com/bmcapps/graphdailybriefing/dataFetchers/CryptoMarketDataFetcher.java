package com.bmcapps.graphdailybriefing.dataFetchers;


import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoMarketDataSchema;
import com.bmcapps.graphdailybriefing.service.CryptoService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;


@DgsComponent
public class CryptoMarketDataFetcher {
    private final CryptoService cryptoService;

    @Autowired
    public CryptoMarketDataFetcher(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @DgsQuery(field = "cryptoMarketData")
    public CryptoMarketDataSchema getCryptoMarketData() {
        return cryptoService.getCryptoMarketData();
    }

}
