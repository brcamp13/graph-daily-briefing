package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.WeatherRequest;
import com.bmcapps.graphdailybriefing.WeatherResponse;
import com.bmcapps.graphdailybriefing.WeatherServiceGrpc;
import com.bmcapps.graphdailybriefing.model.graphSchema.WeatherSchema;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;


@DgsComponent
public class WeatherDataFetcher {

    private final WeatherServiceGrpc.WeatherServiceBlockingStub weatherStub;

    @Autowired
    public WeatherDataFetcher(WeatherServiceGrpc.WeatherServiceBlockingStub weatherStub) {
        this.weatherStub = weatherStub;
    }

    @DgsQuery(field = "weather")
    public WeatherSchema getWeather(@InputArgument String city, @InputArgument String state) {
        WeatherRequest request = WeatherRequest.newBuilder()
                .setCity(city)
                .setState(state)
                .build();
        WeatherResponse response = weatherStub.getWeather(request);
        return new WeatherSchema(
                response.getTemperature(),
                response.getPrecipitation(),
                response.getRelativeHumidity(),
                response.getWindSpeed(),
                response.getWindDirection(),
                response.getWindGusts());
    }
}
