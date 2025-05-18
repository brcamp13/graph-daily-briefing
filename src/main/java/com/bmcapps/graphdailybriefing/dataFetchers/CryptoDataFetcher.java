package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoSchema;
import com.bmcapps.graphdailybriefing.service.CryptoService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DgsComponent
public class CryptoDataFetcher {

    private final CryptoService cryptoService;

    @Autowired
    public CryptoDataFetcher(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @DgsQuery(field = "cryptocurrencies")
    public List<CryptoSchema> getCryptocurrencies(@InputArgument List<String> slugs) {
        return cryptoService.getCryptocurrencies(slugs);
    }
}
