package com.example.demoweb.controller;

//import brave.Span;
//import brave.Tracer;

import com.example.demoweb.dto.ResponseDto;
import com.example.demoweb.feign.DemoFeign;
import com.example.demoweb.feign.DemoFeign2;
import com.example.demoweb.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demoweb.service.TestService;
//import org.springframework.cloud.sleuth.SpanName;
//import org.springframework.cloud.sleuth.annotation.NewSpan;

@Slf4j
@RestController
public class DemoController {
    @Autowired
    private DemoFeign demoFeign;
    @Autowired
    private DemoFeign2 demoFeign2;
    @Autowired
    private TestService testService;


    @GetMapping(value = "/test", produces = "application/json")
    public ResponseDto test() {
        log.info("test");
        return ResponseDto.builder()
                .msg("test")
                .build();
    }

    @GetMapping(value = "/test1", produces = "application/json")
    public ResponseDto test1() {
        log.info("test1");
        return demoFeign.test();
    }


    @GetMapping(value = "/test2", produces = "application/json")
    public ResponseDto test2() {
        log.info("test2");
        testSpan("test2");
        return demoFeign2.test();
//        return ResponseDto.builder()
//                .msg("asdasd")
//                .build();
    }

    @GetMapping(value = "/test3", produces = "application/json")
    public ResponseDto test3() {
        log.info("test2");
        return testService.findCat();
    }

    @NewSpan
    private void testSpan(String s) {
        log.info("new span");

    }


}


