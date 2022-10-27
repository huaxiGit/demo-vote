package com.allen.vote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 启动
 * @author admin
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.allen.vote.mapper"})
public class VoteApplication {
    public static void main(String[] args) {
        SpringApplication.run(VoteApplication.class, args);
    }
}
