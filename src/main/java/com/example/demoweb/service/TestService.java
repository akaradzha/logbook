package com.example.demoweb.service;

//import brave.SpanCustomizer;
//import brave.Tracer;
import com.example.demoweb.dto.ResponseDto;
import com.example.demoweb.repository.CatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestService {

    @Autowired
    CatRepository catRepository;

    @NewSpan
    public ResponseDto findCat() {
        return catRepository.findById(1L)
                .map(cats -> ResponseDto.builder()
                        .msg(cats.getName())
                        .build()
                )
                .orElseThrow(() -> new RuntimeException());
    }

//    @Autowired
//    Tracer tracer;
//    @NewSpan(name = "testSpan2")
//    public void testSpan2(@SpanTag("arg") String arg) {
//        log.info(arg);
////        this.customizer.st
//        try {
//            Thread.sleep(10);
////            tracer.currentSpan().finish();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}
