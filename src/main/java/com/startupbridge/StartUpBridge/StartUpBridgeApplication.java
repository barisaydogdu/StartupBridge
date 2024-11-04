package com.startupbridge.StartUpBridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.filepackage"})
@EnableJpaRepositories(basePackages="com.filepackage.repository")
@EntityScan(basePackages="com.filepackage.entity")



public class StartUpBridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartUpBridgeApplication.class, args);
	}

}
