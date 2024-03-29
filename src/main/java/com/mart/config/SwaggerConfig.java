package com.mart.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.mart.controller"))
				.paths(PathSelectors.regex("/mart.*")).build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		List<VendorExtension> ext = new ArrayList<>();
		ext.add(new StringVendorExtension("test", "value"));
		ApiInfo apiInfo = new ApiInfo("Spring Boot REST API", 
				"Spring Boot REST API for Online Store", 
				"1.0",
				"Terms of service", 
				new Contact(
					"John Thompson", "url", "rasane.vinod@gmail.com"), 
				"Apache License Version 2.0", 
				"https://www.apache.org/licenses/LICENSE-2.0", 
				ext
				);
		return apiInfo;
	}
}
