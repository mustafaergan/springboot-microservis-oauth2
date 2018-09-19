package com.mustafaergan.microservis.admin;

import com.mustafaergan.microservis.admin.service.DatabaseMessageSourceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;


@ComponentScan({"com.mustafaergan.microservis.*"})
@SpringBootApplication
public class AdminApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AdminApplication.class);
	}


	@Bean
	public MessageSource messageSource() {
		MessageSource databaseMessageSource = new DatabaseMessageSourceService();
		return databaseMessageSource;
	}

}
