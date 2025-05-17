package com.bmcapps.graphdailybriefing.mapper;

import com.bmcapps.graphdailybriefing.model.Weather;
import com.bmcapps.graphdailybriefing.model.weatherApi.Current;
import com.bmcapps.graphdailybriefing.model.weatherApi.WeatherApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OpenMeteoGetCurrentToWeatherApiResponseDataMapperTest {

    private OpenMeteoGetCurrentToWeatherApiResponseDataMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new OpenMeteoGetCurrentToWeatherApiResponseDataMapper();
    }

    @Test
    void mapOpenMeteoResponseToDailyBriefingResponse_ShouldMapAllFields() {
        // Arrange
        WeatherApiResponse response = new WeatherApiResponse();
        Current current = new Current();
        current.setTemperature2m(22.5);
        current.setPrecipitation(0.5);
        current.setRelativeHumidity2m(65);
        current.setWindSpeed10m(12.3);
        current.setWindDirection10m(270);
        current.setWindGusts10m(18.7);
        response.setCurrent(current);

        // Act
        Weather result = mapper.mapOpenMeteoResponseToDailyBriefingResponse(response);

        // Assert
        assertNotNull(result);
        assertEquals(22.5, result.getTemperature());
        assertEquals(0.5, result.getPrecipitation());
        assertEquals(65, result.getRelativeHumidity());
        assertEquals(12.3, result.getWindSpeed());
        assertEquals(270, result.getWindDirection());
        assertEquals(18.7, result.getWindGusts());
    }

    @Test
    void mapOpenMeteoResponseToDailyBriefingResponse_WithZeroValues_ShouldMapCorrectly() {
        // Arrange
        WeatherApiResponse response = new WeatherApiResponse();
        Current current = new Current();
        current.setTemperature2m(0.0);
        current.setPrecipitation(0.0);
        current.setRelativeHumidity2m(0);
        current.setWindSpeed10m(0.0);
        current.setWindDirection10m(0);
        current.setWindGusts10m(0.0);
        response.setCurrent(current);

        // Act
        Weather result = mapper.mapOpenMeteoResponseToDailyBriefingResponse(response);

        // Assert
        assertNotNull(result);
        assertEquals(0.0, result.getTemperature());
        assertEquals(0.0, result.getPrecipitation());
        assertEquals(0, result.getRelativeHumidity());
        assertEquals(0.0, result.getWindSpeed());
        assertEquals(0, result.getWindDirection());
        assertEquals(0.0, result.getWindGusts());
    }
}