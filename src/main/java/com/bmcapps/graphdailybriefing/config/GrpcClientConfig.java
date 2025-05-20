package com.bmcapps.graphdailybriefing.config;


import com.bmcapps.graphdailybriefing.WeatherServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {
    @Bean
    public WeatherServiceGrpc.WeatherServiceBlockingStub weatherServiceStub() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        return WeatherServiceGrpc.newBlockingStub(channel);
    }
}
