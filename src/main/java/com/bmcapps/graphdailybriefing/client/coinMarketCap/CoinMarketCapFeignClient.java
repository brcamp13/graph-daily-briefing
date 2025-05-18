package com.bmcapps.graphdailybriefing.client.coinMarketCap;

import com.bmcapps.graphdailybriefing.config.CoinMarketCapFeignConfig;
import com.bmcapps.graphdailybriefing.model.coinMarketCapApi.CoinMarketCapApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.bmcapps.graphdailybriefing.client.coinMarketCap.CoinMarketCapConstants.PARAM_SLUG;


@FeignClient(name = "coinMarketCapClient", url = "https://pro-api.coinmarketcap.com/", configuration = CoinMarketCapFeignConfig.class)
public interface CoinMarketCapFeignClient {
    @GetMapping("v2/cryptocurrency/quotes/latest")
    CoinMarketCapApiResponse getCryptocurrencyQuotes(
            @RequestParam(PARAM_SLUG) String cryptoSlugs
    );
}