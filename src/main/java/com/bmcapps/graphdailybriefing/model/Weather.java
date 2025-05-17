package com.bmcapps.graphdailybriefing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Weather {
    private final double temperature;
    private final double precipitation;
    private final int relativeHumidity;
    private final double windSpeed;
    private final int windDirection;
    private final double windGusts;
}
