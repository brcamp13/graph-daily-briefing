package com.bmcapps.graphdailybriefing.service;

import com.bmcapps.graphdailybriefing.client.openMeteo.OpenMeteoFeignClient;
import com.bmcapps.graphdailybriefing.mapper.OpenMeteoGetCurrentToWeatherApiResponseDataMapper;
import com.bmcapps.graphdailybriefing.model.Weather;
import com.bmcapps.graphdailybriefing.model.weatherApi.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final OpenMeteoFeignClient openMeteoFeignClient;
    private final OpenMeteoGetCurrentToWeatherApiResponseDataMapper mapper;

    @Autowired
    public WeatherService(OpenMeteoFeignClient openMeteoFeignClient,
                          OpenMeteoGetCurrentToWeatherApiResponseDataMapper mapper) {
        this.openMeteoFeignClient = openMeteoFeignClient;
        this.mapper = mapper;
    }

    public Weather getWeatherForLocation(String city, String state) {
        double latitude = 32.76;
        double longitude = 96.79;

        WeatherApiResponse response = openMeteoFeignClient.getWeatherForecast(latitude, longitude);
        return mapper.mapOpenMeteoResponseToDailyBriefingResponse(response);
    }
}
