package com.neuli.dmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 *  @EnableJpaAuditing
 *  : JPA Auditing annotation 을 모두 활성화할 수 있게하는 annotation
 */

@EnableJpaAuditing
@SpringBootApplication
public class DmakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmakerApplication.class, args);
	}

}
