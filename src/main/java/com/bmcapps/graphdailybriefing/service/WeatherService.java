package com.bmcapps.graphdailybriefing.service;

import com.bmcapps.graphdailybriefing.client.openMeteo.OpenMeteoFeignClient;
import com.bmcapps.graphdailybriefing.mapper.OpenMeteoGetCurrentToWeatherSchemaMapper;
import com.bmcapps.graphdailybriefing.model.graphSchema.WeatherSchema;
import com.bmcapps.graphdailybriefing.model.openMeteoApi.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final OpenMeteoFeignClient openMeteoFeignClient;
    private final OpenMeteoGetCurrentToWeatherSchemaMapper mapper;

    @Autowired
    public WeatherService(OpenMeteoFeignClient openMeteoFeignClient,
                          OpenMeteoGetCurrentToWeatherSchemaMapper mapper) {
        this.openMeteoFeignClient = openMeteoFeignClient;
        this.mapper = mapper;
    }

    public WeatherSchema getWeatherForLocation(String city, String state) {
        double latitude = 32.76;
        double longitude = 96.79;

        WeatherApiResponse response = openMeteoFeignClient.getWeatherForecast(latitude, longitude);
        return mapper.mapOpenMeteoResponseToDailyBriefingResponse(response);
    }
}
