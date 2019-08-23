package com.example.demoweb.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.*;

import java.util.List;
import java.util.function.Predicate;

import static org.zalando.logbook.Conditions.exclude;
import static org.zalando.logbook.Conditions.requestTo;

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

    @Bean
//    @ConditionalOnMissingBean({Logbook.class})
    public Logbook logbook(final Predicate<HttpRequest> condition, final List<HeaderFilter> headerFilters, final List<QueryFilter> queryFilters, final List<BodyFilter> bodyFilters, final List<RequestFilter> requestFilters, final List<ResponseFilter> responseFilters, final Strategy strategy, final Sink sink) {
        return Logbook.builder()
                .sink(sink)
                .build();
    }
//    @Bean
//    public FeignClientInterface feignClient(final FeignClient.Builder feignClientBuilder) {
//        return feignClientBuilder
//                .targetClass(FeignClientInterface.class)
//                .name("feign-client-name")
//                .decode404(true)
//                .primary(false)
//                .build();
//    }
//    @Bean("build-by-builder")
//    public TestClient buildByBuilder(final FeignClientBuilder feignClientBuilder) {
//        return feignClientBuilder
//                .forType(TestClient.class, "builderapp")
//                .build();
//    }

//    @
}
