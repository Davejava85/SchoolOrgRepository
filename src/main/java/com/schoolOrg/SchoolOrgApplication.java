package com.schoolOrg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.schoolOrg.domain.repository")
public class SchoolOrgApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolOrgApplication.class, args);
	}

}
