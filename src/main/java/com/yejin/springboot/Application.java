package com.yejin.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.
SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing  //JPA Auditing 활성화
@SpringBootApplication  //스프링부트 자동 설정, Bean 읽기와 생성
//main class, 항상 프로젝트 최상단 위치
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);  //내장 WAS 실행
    }
}
