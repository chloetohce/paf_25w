package paf.workshop.paf_24w;

import java.util.Collections;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import paf.workshop.paf_24w.db.RegistrationRepository;

@SpringBootApplication
public class Paf24wApplication implements CommandLineRunner {

	@Value("${app.name}")
	private String appName;

	@Autowired
	private RegistrationRepository repository;

	private static final Logger logger = Logger.getLogger(Paf24wApplication.class.getName());

	public static void main(String[] args) {
		String name = "acme";
		if (args.length != 0) {
			System.out.println(args[0]);
			name = args[0];
		} 

		SpringApplication app = new SpringApplication(Paf24wApplication.class);
		System.setProperty("app.name", name);
		logger.info("Application started with name: %s".formatted(name));

		// Check if list with key exists in Redis.
		app.run(args);


	}

	@Override
	public void run(String... args) throws Exception {
		if (repository.registerNewList(appName)) {
			logger.info("New registration with %s".formatted(appName));
		} else {
			logger.info(appName + " already registered. ");
		}
	}
		
}
