package com.casestudy.rms;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Driver Class.
 */
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class RmsApplication {

	public static final Logger LOGGER = Logger.getLogger(RmsApplication.class);

	/**
	 * Main method.
	 * 
	 * @param args - arguments
	 */
	public static void main(String[] args) {
		LOGGER.info("RmsApplication :: main");
		SpringApplication.run(RmsApplication.class, args);
	}

}
