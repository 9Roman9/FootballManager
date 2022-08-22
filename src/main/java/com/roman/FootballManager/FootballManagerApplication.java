package com.roman.FootballManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FootballManagerApplication {
	private static ConfigurableApplicationContext context;
	public static void main(String[] args) {
		context = SpringApplication.run(FootballManagerApplication.class, args);
	}

	public static void finish(){
		context.close();
	}

}
