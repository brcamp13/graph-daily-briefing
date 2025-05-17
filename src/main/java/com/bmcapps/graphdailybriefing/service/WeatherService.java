package com.bmcapps.graphdailybriefing.service;


import com.bmcapps.graphdailybriefing.client.openMeteo.OpenMeteoFeignClient;
import com.bmcapps.graphdailybriefing.mapper.OpenMeteoGetCurrentToWeatherApiResponseDataMapper;
import com.bmcapps.graphdailybriefing.model.weatherApi.GetWeatherApiResponseData;
import com.bmcapps.graphdailybriefing.model.weatherApi.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final OpenMeteoFeignClient openMeteoFeignClient;

    private final OpenMeteoGetCurrentToWeatherApiResponseDataMapper openMeteoGetCurrentToWeatherApiResponseDataMapper;

    @Autowired
    public WeatherService(OpenMeteoFeignClient openMeteoFeignClient) {
        this.openMeteoFeignClient = openMeteoFeignClient;
        this.openMeteoGetCurrentToWeatherApiResponseDataMapper = new OpenMeteoGetCurrentToWeatherApiResponseDataMapper();
    }

    public GetWeatherApiResponseData getWeatherForLocation(String city, String state) {
        double latitude = 52.52; // Example value
        double longitude = 13.41; // Example value

        WeatherApiResponse response = openMeteoFeignClient.getWeatherForecast(latitude, longitude);
        return openMeteoGetCurrentToWeatherApiResponseDataMapper.mapOpenMeteoResponseToDailyBriefingResponse(response);
    }
}
