package com.gymadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableAsync
@EnableScheduling
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/assets/node/**").addResourceLocations("/node_modules/");
	    registry.addResourceHandler("/assets/bower/**").addResourceLocations("/bower_components/");
	    registry.addResourceHandler("/css/**").addResourceLocations("/assets/css/");
	    registry.addResourceHandler("/images/**").addResourceLocations("/assets/images/");
	    registry.addResourceHandler("/index.html").addResourceLocations("/index.html");
	    registry.addResourceHandler("/app/**").addResourceLocations("/scripts/app/");
	    registry.addResourceHandler("/components/**").addResourceLocations("/scripts/components/");
	    registry.addResourceHandler("/i18n/**").addResourceLocations("/i18n/");
	}

}
