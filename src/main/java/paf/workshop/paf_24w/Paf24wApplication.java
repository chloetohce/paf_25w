package paf.workshop.paf_24w;

import java.util.Collections;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Paf24wApplication {
	private static final Logger logger = Logger.getLogger(Paf24wApplication.class.getName());

	public static void main(String[] args) {
		String name = "acme";
		if (args.length != 0) {
			name = args[0];
		} 

		SpringApplication app = new SpringApplication(Paf24wApplication.class);
		app.setDefaultProperties(Collections.singletonMap("app.name", name));
		System.out.println("Application started with name: %s".formatted(name));
		app.run(args);
	}
		
}
