package com.example.demoweb.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.*;

import java.util.List;
import java.util.function.Predicate;

@Configuration
public class LogBookConfig {

//    @Bean
//    public Logbook getLogBook() {
//        return Logbook.builder()
////                .condition(exclude(
////                        requestTo("/actuator/**"),
////                        requestTo("/metrics")))
//                .build();
//    }


//
//    @Bean
//    public Logbook logbook(final Predicate<HttpRequest> condition, final List<HeaderFilter> headerFilters, final List<QueryFilter> queryFilters, final List<BodyFilter> bodyFilters, final List<RequestFilter> requestFilters, final List<ResponseFilter> responseFilters, final Strategy strategy, final Sink sink) {
//        return Logbook.builder()
//                .sink(sink)
//                .build();
//    }

//    @Bean
//    @Primary
//    public NewSpanParser getParser(Tracer Tracer){
//        return new MySpanCreator(Tracer);
//    }




}
