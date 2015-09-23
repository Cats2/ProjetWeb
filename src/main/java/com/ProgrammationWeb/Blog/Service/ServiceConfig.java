package com.ProgrammationWeb.Blog.Service;

import javax.inject.Named;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
import org.springframework.context.annotation.Configuration;

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
}
