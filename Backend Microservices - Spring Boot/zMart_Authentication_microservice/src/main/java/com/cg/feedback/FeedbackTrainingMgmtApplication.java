package com.cg.feedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class FeedbackTrainingMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackTrainingMgmtApplication.class, args);
	}

}
