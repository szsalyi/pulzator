package com.github.szsalyi.pulzator.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.github.szsalyi.pulzator")
@EnableJpaRepositories(basePackages = "com.github.szsalyi.pulzator")
@EntityScan(basePackages = "com.github.szsalyi.pulzator")
public class PulzatorApplication {

    public static void main(String... args) {
        SpringApplication.run(PulzatorApplication.class);
    }
}
