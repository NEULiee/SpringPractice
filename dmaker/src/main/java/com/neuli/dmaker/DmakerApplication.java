package com.neuli.dmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 *  @EnableJpaAuditing
 *  : JPA Auditing annotation 을 모두 활성화할 수 있게하는 annotation
 *
 *  테스트 도중 bean 이 동작하지 않는 문제가 발생하여 따로 config 파일을 만들어서 등록
 */

@SpringBootApplication
public class DmakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmakerApplication.class, args);
	}

}
