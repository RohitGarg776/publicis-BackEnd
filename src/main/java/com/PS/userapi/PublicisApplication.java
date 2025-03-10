package com.PS.userapi;

import com.PS.userapi.service.UserService;
import com.PS.userapi.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PublicisApplication {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	public static void main(String[] args) {
		SpringApplication.run(PublicisApplication.class, args);

	}

	@Bean
	CommandLineRunner initDatabase(UserService userService) {

		return args -> {
			int count = userService.loadUsersFromExternalApi();
			log.info("Loaded {} users from external API", count);
		};
	}
}