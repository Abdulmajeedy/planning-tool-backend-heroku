package com.hmy.shuleyangu.systemconfiguration;

import com.hmy.shuleyangu.systemconfiguration.models.SpringSecurityAuditorAware;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SystemConfigurationApplication {
	@Bean
	public AuditorAware<String> auditorAware() {
		return new SpringSecurityAuditorAware();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {

		SpringApplication.run(
				SystemConfigurationApplication.class, args);
	}

}
