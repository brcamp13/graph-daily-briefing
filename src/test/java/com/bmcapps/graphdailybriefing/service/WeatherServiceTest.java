package com.bmcapps.graphdailybriefing.service;


import com.bmcapps.graphdailybriefing.client.openMeteo.OpenMeteoFeignClient;
import com.bmcapps.graphdailybriefing.mapper.OpenMeteoGetCurrentToWeatherSchemaMapper;
import com.bmcapps.graphdailybriefing.model.graphSchema.WeatherSchema;
import com.bmcapps.graphdailybriefing.model.openMeteoApi.WeatherApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private OpenMeteoFeignClient openMeteoFeignClient;

    @Mock
    private OpenMeteoGetCurrentToWeatherSchemaMapper mapper;

    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        weatherService = new WeatherService(openMeteoFeignClient, mapper);
    }

    @Test
    void getWeatherForLocation_ShouldReturnWeather() {
        // Arrange
        String city = "Dallas";
        String state = "TX";
        WeatherApiResponse mockResponse = new WeatherApiResponse();
        WeatherSchema expectedWeather = new WeatherSchema(20.0, 20.0, 3, 10.0, 180, 15.0);

        when(openMeteoFeignClient.getWeatherForecast(anyDouble(), anyDouble())).thenReturn(mockResponse);
        when(mapper.mapOpenMeteoResponseToDailyBriefingResponse(mockResponse)).thenReturn(expectedWeather);

        // Act
        WeatherSchema result = weatherService.getWeatherForLocation(city, state);

        // Assert
        assertNotNull(result);
        assertEquals(expectedWeather.getTemperature(), result.getTemperature());
        verify(openMeteoFeignClient).getWeatherForecast(32.76, 96.79);
        verify(mapper).mapOpenMeteoResponseToDailyBriefingResponse(mockResponse);
    }
}
