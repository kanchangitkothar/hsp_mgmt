package com.jsp.hsp_mgmt_systm.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfig {
	

	public Docket getDocket() {
		Contact contact = new Contact("Accenture", "www.acc.com", "accenture@gmail.com");
		
		List<VendorExtension> list = new ArrayList<VendorExtension>();
		ApiInfo apiInfo = new ApiInfo("HospitalManagementSystem", "online hospital application", "version 1.0", "www.accenture.com", contact, "accenture@gmail.com", "jhgjhg56",list);
		
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.jsp.hsp_mgmt_systm")).build()
				.apiInfo(apiInfo).useDefaultResponseMessages(false);
	}
}