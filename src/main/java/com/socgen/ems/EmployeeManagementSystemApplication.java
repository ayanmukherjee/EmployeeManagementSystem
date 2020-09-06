package com.socgen.ems;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.socgen.ems"))
				.build()
				.apiInfo(createApiInfo());
	}

	private ApiInfo createApiInfo() {
		return new ApiInfo("Employee Management System", "Employee Management System is used to add employees and display the list of employees", 
					"1.0", ApiInfo.DEFAULT.getTermsOfServiceUrl(), 
					new Contact("Ayan Mukherjee", "https://www.linkedin.com/in/ayanmukherjee8/", "ayan.mukherjee22@gmail.com"), 
					ApiInfo.DEFAULT.getLicense(), ApiInfo.DEFAULT.getLicenseUrl(), Collections.emptyList());
	}


}
