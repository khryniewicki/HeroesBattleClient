package com.khryniewicki.projectX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ProjectXApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProjectXApplication.class, args);
		new HelloWorld().start();

	}

}
