package com.travo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
@EnableWebMvc
@ComponentScan
public class ResourceConfig extends WebMvcConfigurerAdapter {
	 private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	          "classpath:/images/",
	            "classpath:/static/", "classpath:/public/" };

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        if (!registry.hasMappingForPattern("/**")) {
	            registry.addResourceHandler("/**").addResourceLocations(
	                    CLASSPATH_RESOURCE_LOCATIONS);
	        }
	    }
}
