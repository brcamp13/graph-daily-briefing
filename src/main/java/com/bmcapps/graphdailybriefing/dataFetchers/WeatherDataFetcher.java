package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.WeatherRequest;
import com.bmcapps.graphdailybriefing.WeatherResponse;
import com.bmcapps.graphdailybriefing.WeatherServiceGrpc;
import com.bmcapps.graphdailybriefing.mapper.WeatherMsResponseToWeatherSchemaMapper;
import com.bmcapps.graphdailybriefing.model.graphSchema.WeatherSchema;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;


@DgsComponent
public class WeatherDataFetcher {

    private final WeatherServiceGrpc.WeatherServiceBlockingStub weatherStub;

    private final WeatherMsResponseToWeatherSchemaMapper mapper;

    @Autowired
    public WeatherDataFetcher(WeatherServiceGrpc.WeatherServiceBlockingStub weatherStub,
                              WeatherMsResponseToWeatherSchemaMapper mapper) {
        this.weatherStub = weatherStub;
        this.mapper = mapper;
    }

    @DgsQuery(field = "weather")
    public WeatherSchema getWeather(@InputArgument String city, @InputArgument String state) {
        WeatherRequest request = WeatherRequest.newBuilder()
                .setCity(city)
                .setState(state)
                .build();
        WeatherResponse response = weatherStub.getWeather(request);
        return mapper.mapWeatherMsResponseToWeatherSchema(response);
    }
}
