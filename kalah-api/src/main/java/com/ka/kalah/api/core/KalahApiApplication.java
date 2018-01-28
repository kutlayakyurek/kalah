package com.ka.kalah.api.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Description: Kalah restful web services application
 * Project: kalah-api
 * Package: com.ka.kalah.api.core
 * Author: kakyurek
 * Date: 2018.01.27
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.ka.kalah.api")
@EntityScan(basePackages = "com.ka.kalah.api.model.persistence")
@EnableJpaRepositories(basePackages = "com.ka.kalah.api.repository")
public class KalahApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KalahApiApplication.class, args);
    }

}