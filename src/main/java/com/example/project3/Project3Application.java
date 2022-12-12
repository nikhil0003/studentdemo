package com.example.project3;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project3Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Project3Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Project3Application.class);
	}

	@Bean
	public ObjectMapper createObjectMapper() {
		return new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

}
