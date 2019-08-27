package com.example.demoweb.controller;

import brave.Span;
import brave.Tracer;
import com.example.demoweb.dto.ResponseDto;
import com.example.demoweb.feign.DemoFeign;
import com.example.demoweb.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.SpanName;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {
    @Autowired
    private DemoFeign demoFeign;
    @Autowired
    private TestService testService;


    @Autowired
    Tracer tracer;
    @GetMapping(value = "/test", produces = "application/json")
    public ResponseDto test(){
        log.info("test");
        testService.testSpan2("arrrr");
        return ResponseDto.builder()
                .msg("test")
                .build();
    }

    @GetMapping(value = "/test2", produces = "application/json")
    public ResponseDto test2(){
        log.info("test2");
        testSpan("test");
        return demoFeign.test();
    }

//    @NewSpan
    private void testSpan(String s) {
      log.info("new span");

        Span newSpan = tracer.nextSpan().name("newSpan").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("I'm in the new span doing some cool work that needs its own span");
        } finally {
            newSpan.finish();
        }
//        testSpan2();
    }


}


