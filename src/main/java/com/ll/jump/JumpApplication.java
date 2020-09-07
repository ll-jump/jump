package com.ll.jump;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.ll.jump.mapper")
@EnableAsync
//@ImportResource(locations = {"classpath:META-INF/ctx/ctx_main.xml"})
public class JumpApplication {

    public static void main(String[] args) {
        SpringApplication.run(JumpApplication.class, args);
    }

}
