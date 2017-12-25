package com.github.weiwei02.springcloud.task.batch.peoplebatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableBatchProcessing
@EnableAspectJAutoProxy
@SpringBootApplication
public class PeopleBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleBatchApplication.class, args);
	}
}
