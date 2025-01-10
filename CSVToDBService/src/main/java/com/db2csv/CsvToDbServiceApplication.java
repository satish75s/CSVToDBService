package com.db2csv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="CSV to Database", version="v1.0", description="Demo Application")) 

public class CsvToDbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvToDbServiceApplication.class, args);
	}

}
