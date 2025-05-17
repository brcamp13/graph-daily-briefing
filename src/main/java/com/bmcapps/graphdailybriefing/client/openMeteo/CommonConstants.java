package com.bmcapps.graphdailybriefing.client.openMeteo;


public class CommonConstants {
    // Endpoint
    public static final String FORECAST_ENDPOINT = "/forecast";

    // Request parameter names
    public static final String PARAM_LATITUDE = "latitude";
    public static final String PARAM_LONGITUDE = "longitude";
    public static final String PARAM_CURRENT = "current";
    public static final String PARAM_WIND_SPEED_UNIT = "wind_speed_unit";
    public static final String PARAM_TEMPERATURE_UNIT = "temperature_unit";
    public static final String PARAM_PRECIPITATION_UNIT = "precipitation_unit";

    // Parameter values
    public static final String VALUE_CURRENT_PARAMS = "temperature_2m,precipitation,relative_humidity_2m,wind_speed_10m,wind_direction_10m,wind_gusts_10m";
    public static final String VALUE_WIND_SPEED_MPH = "mph";
    public static final String VALUE_TEMPERATURE_FAHRENHEIT = "fahrenheit";
    public static final String VALUE_PRECIPITATION_INCH = "inch";
}
