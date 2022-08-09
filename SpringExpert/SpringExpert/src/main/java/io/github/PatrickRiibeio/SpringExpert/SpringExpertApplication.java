package io.github.PatrickRiibeio.SpringExpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "io.github.PatrickRiibeio.SpringExpert.model.repository",
		"io.github.PatrickRiibeio.SpringExpert.service" })
@SpringBootApplication
public class SpringExpertApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringExpertApplication.class, args);
	}

}
