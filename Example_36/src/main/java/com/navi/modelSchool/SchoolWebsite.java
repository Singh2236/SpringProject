package com.navi.modelSchool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableJpaRepositories("com.navi.modelSchool.repository")
@EntityScan("com.navi.modelSchool.model")
public class SchoolWebsite {

    public static void main(String[] args) {
        SpringApplication.run(SchoolWebsite.class, args);
    }

}
