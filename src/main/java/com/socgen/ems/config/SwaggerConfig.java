package com.socgen.ems.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.socgen.ems"))
				.build()
				.apiInfo(createApiInfo());
	}

	private ApiInfo createApiInfo() {
		return new ApiInfo("Employee Management System", "Employee Management System is a service that enables you to create an employee, fetch employee details and show the list of employees in the system.", 
					"1.0", null, 
					new Contact("Ayan Mukherjee", "https://www.linkedin.com/in/ayanmukherjee8/", "ayan.mukherjee22@gmail.com"), 
					ApiInfo.DEFAULT.getLicense(), ApiInfo.DEFAULT.getLicenseUrl(), Collections.emptyList());
	}
}
