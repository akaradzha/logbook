package com.example.demoweb.controller;

import com.example.demoweb.dto.ResponseDto;
import com.example.demoweb.feign.DemoFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private DemoFeign demoFeign;

    @GetMapping(value = "/test", produces = "application/json")
    public ResponseDto test(){
        return ResponseDto.builder()
                .msg("test")
                .build();
    }

    @GetMapping(value = "/test2", produces = "application/json")
    public ResponseDto test2(){
        return demoFeign.test();
    }

}


