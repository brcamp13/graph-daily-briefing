package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.WeatherRequest;
import com.bmcapps.graphdailybriefing.WeatherResponse;
import com.bmcapps.graphdailybriefing.WeatherServiceGrpc;
import com.bmcapps.graphdailybriefing.mapper.WeatherMsResponseToWeatherSchemaMapper;
import com.bmcapps.graphdailybriefing.model.graphSchema.WeatherSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherDataFetcherTest {

    @Mock
    private WeatherServiceGrpc.WeatherServiceBlockingStub weatherStub;

    @Mock
    private WeatherMsResponseToWeatherSchemaMapper mapper;

    private WeatherDataFetcher weatherDataFetcher;

    @BeforeEach
    void setUp() {
        weatherDataFetcher = new WeatherDataFetcher(weatherStub, mapper);
    }

    @Test
    void getWeather_ShouldReturnWeatherForLocation() {
        // Arrange
        String city = "Dallas";
        String state = "TX";
        WeatherRequest request = WeatherRequest.newBuilder()
                .setCity(city)
                .setState(state)
                .build();
        WeatherResponse response = WeatherResponse.newBuilder()
                .setTemperature(22.5)
                .setPrecipitation(0.5)
                .setRelativeHumidity(65)
                .setWindSpeed(12.3)
                .setWindDirection(270)
                .setWindGusts(18.7)
                .build();
        WeatherSchema expectedWeather = new WeatherSchema(22.5, 0.5, 65, 12.3, 270, 18.7);

        when(weatherStub.getWeather(request)).thenReturn(response);
        when(mapper.mapWeatherMsResponseToWeatherSchema(response)).thenReturn(expectedWeather);

        // Act
        WeatherSchema result = weatherDataFetcher.getWeather(city, state);

        // Assert
        assertNotNull(result);
        assertEquals(expectedWeather.getTemperature(), result.getTemperature());
        assertEquals(expectedWeather.getPrecipitation(), result.getPrecipitation());
        assertEquals(expectedWeather.getRelativeHumidity(), result.getRelativeHumidity());
        assertEquals(expectedWeather.getWindSpeed(), result.getWindSpeed());
        assertEquals(expectedWeather.getWindDirection(), result.getWindDirection());
        assertEquals(expectedWeather.getWindGusts(), result.getWindGusts());
        verify(weatherStub).getWeather(request);
        verify(mapper).mapWeatherMsResponseToWeatherSchema(response);
    }
}