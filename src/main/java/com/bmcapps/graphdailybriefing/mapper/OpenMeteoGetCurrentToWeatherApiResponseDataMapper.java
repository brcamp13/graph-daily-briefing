package com.bmcapps.graphdailybriefing.mapper;

import com.bmcapps.graphdailybriefing.model.Weather;
import com.bmcapps.graphdailybriefing.model.weatherApi.GetWeatherApiResponseData;
import com.bmcapps.graphdailybriefing.model.weatherApi.WeatherApiResponse;

public class OpenMeteoGetCurrentToWeatherApiResponseDataMapper {

    public Weather mapOpenMeteoResponseToDailyBriefingResponse(WeatherApiResponse response) {
        Weather weatherDataFromApi = new Weather(
                response.getCurrent().getTemperature2m(),
                response.getCurrent().getPrecipitation(),
                response.getCurrent().getRelativeHumidity2m(),
                response.getCurrent().getWindSpeed10m(),
                response.getCurrent().getWindDirection10m(),
                response.getCurrent().getWindGusts10m()
        );

        return weatherDataFromApi;
    }
}
