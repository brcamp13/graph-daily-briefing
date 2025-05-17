package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.model.Weather;
import com.bmcapps.graphdailybriefing.service.WeatherService;
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
    private WeatherService weatherService;

    private WeatherDataFetcher weatherDataFetcher;

    @BeforeEach
    void setUp() {
        weatherDataFetcher = new WeatherDataFetcher(weatherService);
    }

    @Test
    void getWeather_ShouldReturnWeatherForLocation() {
        // Arrange
        String city = "Dallas";
        String state = "TX";
        Weather expectedWeather = new Weather(22.5, 0.5, 65, 12.3, 270, 18.7);

        when(weatherService.getWeatherForLocation(city, state)).thenReturn(expectedWeather);

        // Act
        Weather result = weatherDataFetcher.getWeather(city, state);

        // Assert
        assertNotNull(result);
        assertEquals(expectedWeather.getTemperature(), result.getTemperature());
        assertEquals(expectedWeather.getPrecipitation(), result.getPrecipitation());
        assertEquals(expectedWeather.getRelativeHumidity(), result.getRelativeHumidity());
        assertEquals(expectedWeather.getWindSpeed(), result.getWindSpeed());
        assertEquals(expectedWeather.getWindDirection(), result.getWindDirection());
        assertEquals(expectedWeather.getWindGusts(), result.getWindGusts());
        verify(weatherService).getWeatherForLocation(city, state);
    }
}