package com.example.Location_Voitures;

import org.springframework.boot.SpringApplication;

public class TestLocationVoituresApplication {

	public static void main(String[] args) {
		SpringApplication.from(LocationVoituresApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
