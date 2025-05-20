package com.bmcapps.graphdailybriefing.config;


import com.bmcapps.graphdailybriefing.WeatherServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class GrpcClientConfig {

    private ManagedChannel channel;

    @Bean
    public WeatherServiceGrpc.WeatherServiceBlockingStub weatherServiceStub() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        return WeatherServiceGrpc.newBlockingStub(channel);
    }

    @PreDestroy
    public void shutdownChannel() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }
    }
}
