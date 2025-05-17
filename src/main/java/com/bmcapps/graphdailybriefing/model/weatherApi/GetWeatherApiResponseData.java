package com.bmcapps.graphdailybriefing.model.weatherApi;

import lombok.Getter;

@Getter
public class GetWeatherApiResponseData {
    private final double temperature;
    private final double precipitation;
    private final int relativeHumidity;
    private final double windSpeed;
    private final int windDirection;
    private final double windGusts;

    public GetWeatherApiResponseData(
            double temperature,
            double precipitation,
            int relativeHumidity,
            double windSpeed,
            int windDirection,
            double windGusts
    ) {
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.relativeHumidity = relativeHumidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.windGusts = windGusts;
    }

}
