//package com.example.demoweb;
//
//import com.example.demoweb.feign.DemoFeign;
//import feign.Client;
//import feign.Feign;
//import feign.codec.Decoder;
//import feign.codec.Encoder;
//import okhttp3.ConnectionPool;
//import okhttp3.OkHttpClient;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.cloud.commons.httpclient.OkHttpClientConnectionPoolFactory;
//import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.cloud.openfeign.FeignClientsConfiguration;
//import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
//import org.springframework.cloud.openfeign.support.SpringMvcContract;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
//import java.util.concurrent.TimeUnit;
//
////import org.springframework.cloud.netflix.feign.support.FeignHttpClientProperties;
//
//@Configuration
//@Import(FeignClientsConfiguration.class)
//public class FeignClientLoggerConfiguration {
//
//    @Bean
//    public DemoFeign createFeign(Decoder decoder, Encoder encoder, Client client) {
//        return Feign.builder().client(client)
//                .contract(new SpringMvcContract())
//                .encoder(encoder)
//                .decoder(decoder)
//                .target(DemoFeign.class, "http://localhost:8080");
//    }
//
//    @Bean
//    public ConnectionPool httpClientConnectionPool(FeignHttpClientProperties httpClientProperties, OkHttpClientConnectionPoolFactory connectionPoolFactory) {
//        Integer maxTotalConnections = httpClientProperties.getMaxConnections();
//        Long timeToLive = httpClientProperties.getTimeToLive();
//        TimeUnit ttlUnit = httpClientProperties.getTimeToLiveUnit();
//        return connectionPoolFactory.create(maxTotalConnections, timeToLive, ttlUnit);
//    }
//
//    @Bean
//    public okhttp3.OkHttpClient client(OkHttpClientFactory httpClientFactory, ConnectionPool connectionPool, FeignHttpClientProperties httpClientProperties) {
//        Boolean followRedirects = httpClientProperties.isFollowRedirects();
//        Integer connectTimeout = httpClientProperties.getConnectionTimeout();
//        return httpClientFactory.createBuilder(false).connectTimeout(connectTimeout, TimeUnit.MILLISECONDS).followRedirects(followRedirects).connectionPool(connectionPool)
////                .addInterceptor(new HttpLoggerInterceptor())
//                .build();
//    }
//
//	@Bean
//	public Client feignClient(okhttp3.OkHttpClient client) {
//		return new feign.okhttp.OkHttpClient(client);
//	}
//
//
//}
