package com.ProgrammationWeb.Blog.Service;

import javax.inject.Named;
import javax.servlet.MultipartConfigElement;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class ServiceConfig {
	
	@Named
	@ApplicationPath("/api")
	static class JerseyConfig extends ResourceConfig {
		public JerseyConfig() {
			this.packages("com.ProgrammationWeb.Blog.RestAPI");
			
			// Create a recursive package scanner
	        PackageNamesScanner resourceFinder = new PackageNamesScanner(
	                new String[] { "X.service" }, true);
	        // Register the scanner with this Application
	        registerFinder(resourceFinder);
	        register(JacksonFeature.class);
	        // Mise en place du filtre
	        register(SimpleCORSFilter.class);
		}
	}
	
	@Bean
	 public MultipartConfigElement multipartConfigElement() {
	     return new MultipartConfigElement("");
	 }

	 @Bean
	 public MultipartResolver multipartResolver() {
	     org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
	     multipartResolver.setMaxUploadSize(1000000);
	     return multipartResolver;
	 }
	
	
}
