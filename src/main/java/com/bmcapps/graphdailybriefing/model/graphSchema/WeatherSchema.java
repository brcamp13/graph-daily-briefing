package com.bmcapps.graphdailybriefing.model.graphSchema;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeatherSchema {
    private final double temperature;
    private final double precipitation;
    private final int relativeHumidity;
    private final double windSpeed;
    private final int windDirection;
    private final double windGusts;
}
