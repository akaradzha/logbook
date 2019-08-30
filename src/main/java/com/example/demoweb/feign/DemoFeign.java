package com.example.demoweb.feign;

import com.example.demoweb.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="test", url = "http://localhost:8082")
public interface DemoFeign {

    @GetMapping(value = "/test2")
    ResponseDto test();

}
