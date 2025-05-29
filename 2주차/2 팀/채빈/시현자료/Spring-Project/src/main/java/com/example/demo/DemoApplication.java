package com.example.demo;  // 패키지명 명확히 지정

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {  // 유일한 메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}