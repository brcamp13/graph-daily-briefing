package com.bmcapps.graphdailybriefing.config;


import com.bmcapps.graphdailybriefing.CryptoServiceGrpc;
import com.bmcapps.graphdailybriefing.WeatherServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class GrpcClientConfig {

    private ManagedChannel weatherChannel;
    private ManagedChannel cryptoChannel;

    @Bean
    public WeatherServiceGrpc.WeatherServiceBlockingStub weatherServiceStub() {
        weatherChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        return WeatherServiceGrpc.newBlockingStub(weatherChannel);
    }

    @Bean
    public CryptoServiceGrpc.CryptoServiceBlockingStub cryptoServiceStub() {
        cryptoChannel = ManagedChannelBuilder.forAddress("localhost", 9091)
                .usePlaintext()
                .build();
        return CryptoServiceGrpc.newBlockingStub(cryptoChannel);
    }

    @PreDestroy
    public void shutdownChannels() {
        if (weatherChannel != null && !weatherChannel.isShutdown()) {
            weatherChannel.shutdown();
        }
        if (cryptoChannel != null && !cryptoChannel.isShutdown()) {
            cryptoChannel.shutdown();
        }
    }
}
