package com.bmcapps.graphdailybriefing.mapper;


import com.bmcapps.graphdailybriefing.WeatherResponse;
import com.bmcapps.graphdailybriefing.model.graphSchema.WeatherSchema;
import org.springframework.stereotype.Component;

@Component
public class WeatherMsResponseToWeatherSchemaMapper {

    public WeatherSchema mapWeatherMsResponseToWeatherSchema(WeatherResponse response) {
        return new WeatherSchema(
                response.getTemperature(),
                response.getPrecipitation(),
                response.getRelativeHumidity(),
                response.getWindSpeed(),
                response.getWindDirection(),
                response.getWindGusts()
        );
    }
}
