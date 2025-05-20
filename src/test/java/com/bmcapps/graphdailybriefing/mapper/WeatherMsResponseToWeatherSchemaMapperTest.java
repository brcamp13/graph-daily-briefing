package com.bmcapps.graphdailybriefing.mapper;

import com.bmcapps.graphdailybriefing.WeatherResponse;
import com.bmcapps.graphdailybriefing.model.graphSchema.WeatherSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherMsResponseToWeatherSchemaMapperTest {

    private final WeatherMsResponseToWeatherSchemaMapper mapper = new WeatherMsResponseToWeatherSchemaMapper();

    @Test
    void testMapWeatherMsResponseToWeatherSchema() {
        // Arrange
        WeatherResponse response = WeatherResponse.newBuilder()
                .setTemperature(25.0)
                .setPrecipitation(10.0)
                .setRelativeHumidity(60)
                .setWindSpeed(15.0)
                .setWindDirection(10)
                .setWindGusts(20.0)
                .build();

        // Act
        WeatherSchema result = mapper.mapWeatherMsResponseToWeatherSchema(response);

        // Assert
        assertEquals(25.0, result.getTemperature());
        assertEquals(10.0, result.getPrecipitation());
        assertEquals(60, result.getRelativeHumidity());
        assertEquals(15.0, result.getWindSpeed());
        assertEquals(10, result.getWindDirection());
        assertEquals(20.0, result.getWindGusts());
    }
}