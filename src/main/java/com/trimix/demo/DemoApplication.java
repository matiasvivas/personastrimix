package com.trimix.demo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Docket getDocket(){
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.trimix.demo"))
			.paths(PathSelectors.any())
			.build().apiInfo(createApiInfo());
	}

	//setting api info
	private ApiInfo createApiInfo() {
		return new ApiInfo("Persona Api",
			"Aplicacion ABM de personas para Trimix",
			"1.00",
			"matiasalexandervivas@gmail.com",
			new Contact("Matias", "matiasalexandervivas@gmail.com", "matiasalexandervivas@gmail.com"),
			"Libre",
			"matiasalexandervivas@gmail.com",
			Collections.emptyList());
	}

}
