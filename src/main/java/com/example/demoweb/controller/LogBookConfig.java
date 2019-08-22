package com.example.demoweb.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;

import static org.zalando.logbook.Conditions.exclude;
import static org.zalando.logbook.Conditions.requestTo;

@Configuration
public class LogBookConfig {

    @Bean
    public Logbook getLogBook() {
        return Logbook.builder()
//                .condition(exclude(
//                        requestTo("/actuator/**"),
//                        requestTo("/metrics")))
                .build();
    }
}
