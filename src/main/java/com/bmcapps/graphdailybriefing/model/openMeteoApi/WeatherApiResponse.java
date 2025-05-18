package com.bmcapps.graphdailybriefing.model.openMeteoApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherApiResponse {
    private double latitude;
    private double longitude;

    @JsonProperty("generationtime_ms")
    private double generationTimeMs;

    @JsonProperty("utc_offset_seconds")
    private int utcOffsetSeconds;

    private String timezone;

    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;

    private double elevation;

    @JsonProperty("current_units")
    private CurrentUnits currentUnits;

    private Current current;
}
