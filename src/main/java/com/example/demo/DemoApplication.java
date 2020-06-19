package com.example.demo;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@SpringBootApplication
@Configuration
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Test1 test1 = new Test1(LocalDateTime.now());
		Test2 test2 = new Test2(LocalDateTime.now().plus(1, ChronoUnit.DAYS));
		System.out.println("Now: " + test1.getTime());
		System.out.println("Tomorrow: " + test2.getTime());

		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		Test2 test3 = mapper.map(test1, Test2.class);
		System.out.println("Test3: " + test3.getTime());
	}
}
