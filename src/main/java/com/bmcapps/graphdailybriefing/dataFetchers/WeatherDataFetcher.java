package com.bmcapps.graphdailybriefing.dataFetchers;

import com.bmcapps.graphdailybriefing.model.Weather;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;


@DgsComponent
public class WeatherDataFetcher {

    @DgsQuery(field = "weather")
            public Weather getWeather() {
        return new Weather(25.0f, "Sunny");
    }
}
