package com.bmcapps.graphdailybriefing.model.openMeteoApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CurrentUnits {
    private String time;
    private String interval;

    @JsonProperty("temperature_2m")
    private String temperature2m;

    private String precipitation;

    @JsonProperty("relative_humidity_2m")
    private String relativeHumidity2m;

    @JsonProperty("wind_speed_10m")
    private String windSpeed10m;

    @JsonProperty("wind_direction_10m")
    private String windDirection10m;

    @JsonProperty("wind_gusts_10m")
    private String windGusts10m;
}
