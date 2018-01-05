package com.eficode.buggywebservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodereviewApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(CodereviewApplication.class);
	
	public static void main(String[] args) {
		LOGGER.debug("***** SpringApplication START *****");
		SpringApplication.run(CodereviewApplication.class, args);
	}
}
