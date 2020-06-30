package com.karader.irs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:configuration.properties")
public class KaraderMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaraderMvcApplication.class, args);
	}

}
