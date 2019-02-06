package com.vivid.partnerships.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

@SpringBootApplication
public class InterviewApplication {

    @Bean
    public TimeZone timeZone() {
        TimeZone defaultTimeZone = TimeZone.getTimeZone("UTC");
        TimeZone.setDefault(defaultTimeZone);
        return defaultTimeZone;
    }

    public static void main(String[] args) {
        SpringApplication.run(InterviewApplication.class, args);
    }
}
