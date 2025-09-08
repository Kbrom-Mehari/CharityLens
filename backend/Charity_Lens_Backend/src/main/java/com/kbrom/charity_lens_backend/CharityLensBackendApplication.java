package com.kbrom.charity_lens_backend;

import com.kbrom.charity_lens_backend.common.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class CharityLensBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharityLensBackendApplication.class, args);
	}

}
