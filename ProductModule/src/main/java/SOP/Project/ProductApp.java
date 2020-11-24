package SOP.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ProductApp {
	
	public static void main(String[] args) {
		SpringApplication.run(ProductApp.class, args);
	}
}