package com.bmcapps.graphdailybriefing.mapper;

import com.bmcapps.graphdailybriefing.model.graphSchema.WeatherSchema;
import com.bmcapps.graphdailybriefing.model.openMeteoApi.WeatherApiResponse;
import org.springframework.stereotype.Component;


@Component
public class OpenMeteoGetCurrentToWeatherSchemaMapper {

    public WeatherSchema mapOpenMeteoResponseToDailyBriefingResponse(WeatherApiResponse response) {
        WeatherSchema weatherDataFromApi = new WeatherSchema(
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
