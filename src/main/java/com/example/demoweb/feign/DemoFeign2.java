package com.example.demoweb.feign;

import com.example.demoweb.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="test2", url = "http://localhost:8082")
public interface DemoFeign2 {

    @GetMapping(value = "/test3")
    ResponseDto test();

}
