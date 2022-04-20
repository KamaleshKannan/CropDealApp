package kam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"kam.farmer"})
@SpringBootApplication
public class FarmerSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmerSpringApplication.class, args);
	}

}
