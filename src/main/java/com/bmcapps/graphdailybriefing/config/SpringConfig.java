package com.bmcapps.graphdailybriefing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;



@Configuration
@PropertySource("classpath:secrets.properties")
public class SpringConfig {
}
