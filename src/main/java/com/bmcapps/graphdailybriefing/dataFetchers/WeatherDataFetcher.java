package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.model.Weather;
import com.bmcapps.graphdailybriefing.model.weatherApi.GetWeatherApiResponseData;
import com.bmcapps.graphdailybriefing.service.WeatherService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;


@DgsComponent
public class WeatherDataFetcher {

    private final WeatherService weatherService;

    @Autowired
    public WeatherDataFetcher(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @DgsQuery(field = "weather")
    public Weather getWeather(@InputArgument String city, @InputArgument String state) {
        return weatherService.getWeatherForLocation(city, state);
    }
}
