package com.bmcapps.graphdailybriefing.client.openMeteo;

import com.bmcapps.graphdailybriefing.model.weatherApi.WeatherApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.bmcapps.graphdailybriefing.client.openMeteo.CommonConstants.*;

@FeignClient(name = "openMeteoClient", url = "https://api.open-meteo.com/v1")
public interface OpenMeteoFeignClient {

    @GetMapping(FORECAST_ENDPOINT)
    WeatherApiResponse getWeatherForecast(
            @RequestParam(PARAM_LATITUDE) double latitude,
            @RequestParam(PARAM_LONGITUDE) double longitude,
            @RequestParam(value = PARAM_CURRENT, defaultValue = VALUE_CURRENT_PARAMS, required = false) String current,
            @RequestParam(value = PARAM_WIND_SPEED_UNIT, defaultValue = VALUE_WIND_SPEED_MPH, required = false) String windSpeedUnit,
            @RequestParam(value = PARAM_TEMPERATURE_UNIT, defaultValue = VALUE_TEMPERATURE_FAHRENHEIT, required = false) String temperatureUnit,
            @RequestParam(value = PARAM_PRECIPITATION_UNIT, defaultValue = VALUE_PRECIPITATION_INCH, required = false) String precipitationUnit
    );

    // Add a simplified overload that only requires coordinates
    @GetMapping(FORECAST_ENDPOINT)
    default WeatherApiResponse getWeatherForecast(
            @RequestParam(PARAM_LATITUDE) double latitude,
            @RequestParam(PARAM_LONGITUDE) double longitude
    ) {
        return getWeatherForecast(
                latitude,
                longitude,
                VALUE_CURRENT_PARAMS,
                VALUE_WIND_SPEED_MPH,
                VALUE_TEMPERATURE_FAHRENHEIT,
                VALUE_PRECIPITATION_INCH
        );
    }
}