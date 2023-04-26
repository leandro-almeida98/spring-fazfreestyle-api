package veexi.fazfreestyle.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@PropertySource("classpath:config.properties")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
