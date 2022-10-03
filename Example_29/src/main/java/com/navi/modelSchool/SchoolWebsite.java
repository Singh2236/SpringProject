package com.navi.modelSchool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class 	SchoolWebsite {

	public static void main(String[] args) {
		SpringApplication.run(SchoolWebsite.class, args);
	}

}
