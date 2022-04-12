package com.yejin.springboot.web;

import com.yejin.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.
RestController;

@RestController  //JSON을 반환하는 컨트롤러
public class HelloController {

    @GetMapping("/hello")  //Get 요청을 받을 API
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")  //Get 요청을 받을 API
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {  //외부에서 API로 넘긴 파라미터를 가져옴
        return new HelloResponseDto(name, amount);
    }
}
