//package com.example.demoweb.controller;
//
//import com.example.demoweb.log.InlineQueryLogEntryCreator;
////import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
//import net.ttddyy.dsproxy.listener.SLF4JQueryLoggingListener;
//import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
//import org.postgresql.ds.PGSimpleDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//public class DataSourceProxyConfig {
//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
//        loggingListener.setQueryLogEntryCreator(new InlineQueryLogEntryCreator());
//        return ProxyDataSourceBuilder
//                .create(getH2DataSource())
//                .name("MyDS")
//                .listener(loggingListener)
//                .build();
//    }
//
//    private static DataSource getH2DataSource() {
//        PGSimpleDataSource ds = new PGSimpleDataSource() ;
//        ds.setURL("jdbc:postgresql://localhost:5432/");
//        ds.setUser("postgres");
//        ds.setPassword("postgres");
//        return ds;
//    }
//}
