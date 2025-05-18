package com.bmcapps.graphdailybriefing.model.openMeteoApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Current {
    private String time;
    private int interval;

    @JsonProperty("temperature_2m")
    private double temperature2m;

    private double precipitation;

    @JsonProperty("relative_humidity_2m")
    private int relativeHumidity2m;

    @JsonProperty("wind_speed_10m")
    private double windSpeed10m;

    @JsonProperty("wind_direction_10m")
    private int windDirection10m;

    @JsonProperty("wind_gusts_10m")
    private double windGusts10m;
}
