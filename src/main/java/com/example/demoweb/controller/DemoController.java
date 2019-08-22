package com.example.demoweb.controller;

import com.example.demoweb.dto.ResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DemoController {
    @GetMapping("/test")
    public ResponseDto test(){
        return ResponseDto.builder()
                .msg("test")
                .build();
    }

}


