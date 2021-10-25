package com.geniessoft.backend;

import com.geniessoft.backend.service.DatabasePopulationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class Application {

	private final DatabasePopulationService databasePopulationService;

	public Application(final DatabasePopulationService databasePopulationService) {
		this.databasePopulationService = databasePopulationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void populateDatabase() {
		databasePopulationService.populateDatabase();
	}

}
