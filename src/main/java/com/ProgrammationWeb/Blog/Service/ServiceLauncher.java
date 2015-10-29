package com.ProgrammationWeb.Blog.Service;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class ServiceLauncher {

	public static void main(String[] args) {
		SpringApplication.run(ServiceLauncher.class, args);
	}
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
	return new MultipartConfigElement("");
	}
	
}

