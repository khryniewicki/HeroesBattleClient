package com.khryniewicki.projectX;

import com.khryniewicki.projectX.config.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ProjectXApplication {

	public static void main(String[] args) {
		Runnable application = new Application();
		Thread thread = new Thread(application);
		thread.setName("Websocket");

		SpringApplication.run(ProjectXApplication.class, args);
		thread.start();
		new HelloWorld().start();
	}

}
