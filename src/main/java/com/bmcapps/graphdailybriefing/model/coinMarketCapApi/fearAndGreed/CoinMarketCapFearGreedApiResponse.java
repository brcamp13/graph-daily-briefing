package com.bmcapps.graphdailybriefing.model.coinMarketCapApi.fearAndGreed;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketCapFearGreedApiResponse {
    private DataResponse data;
}
