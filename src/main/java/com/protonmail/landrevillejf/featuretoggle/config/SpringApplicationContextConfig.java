package com.protonmail.landrevillejf.featuretoggle.config;


import com.protonmail.landrevillejf.featuretoggle.SpringApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApplicationContextConfig {
    @Bean
    public SpringApplicationContext springApplicationContext() {
        return new SpringApplicationContext();
    }
}
