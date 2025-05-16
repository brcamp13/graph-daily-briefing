package com.bmcapps.graphdailybriefing.model;

public class Weather {
    private final Float temperature;
    private final String description;

    public Weather(Float temperature, String description) {
        this.temperature = temperature;
        this.description = description;
    }

    public Float getTemperature() { return temperature; }
    public String getDescription() { return description; }
}
