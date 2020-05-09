package com.khryniewicki.projectX;

import com.khryniewicki.projectX.config.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ProjectXApplication {

	public static void main(String[] args) {
		Application application = new Application();
		SpringApplication.run(ProjectXApplication.class, args);
		application.startWebsocket();
		new HelloWorld().start();	}

}
