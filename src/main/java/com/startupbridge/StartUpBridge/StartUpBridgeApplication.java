package com.startupbridge.StartUpBridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
//@ComponentScan(basePackages = "com.filepackage")  // Controller'in bulunduğu ana paketi tarar
//@EnableJpaRepositories(basePackages = "com.filepackage.repository") // Repository paketini belirtin

public class StartUpBridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartUpBridgeApplication.class, args);
	}

}
