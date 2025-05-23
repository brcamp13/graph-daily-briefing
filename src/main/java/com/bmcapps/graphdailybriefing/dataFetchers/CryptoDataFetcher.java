package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.CryptoRequest;
import com.bmcapps.graphdailybriefing.CryptoResponse;
import com.bmcapps.graphdailybriefing.CryptoServiceGrpc;
import com.bmcapps.graphdailybriefing.mapper.CryptoMsResponseToCryptocurrencySchemaMapper;
import com.bmcapps.graphdailybriefing.model.graphSchema.CryptoSchema;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


@DgsComponent
public class CryptoDataFetcher {

    private final CryptoServiceGrpc.CryptoServiceBlockingStub cryptoStub;

    private final CryptoMsResponseToCryptocurrencySchemaMapper mapper;

    @Autowired
    public CryptoDataFetcher(CryptoServiceGrpc.CryptoServiceBlockingStub cryptoStub,
                             CryptoMsResponseToCryptocurrencySchemaMapper mapper) {
        this.cryptoStub = cryptoStub;
        this.mapper = mapper;
    }

    @DgsQuery(field = "cryptocurrencies")
    public List<CryptoSchema> getCryptocurrencies(@InputArgument List<String> slugs) {
        CryptoRequest request = CryptoRequest.newBuilder().addAllSlugs(slugs).build();
        CryptoResponse response = cryptoStub.getCryptoCurrencies(request);
        return response.getCurrenciesList().stream()
                .map(mapper::mapCryptoMsResponseToCryptoSchema)
                .collect(Collectors.toList());
    }
}
