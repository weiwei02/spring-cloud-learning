package com.github.weiwei02.springcloud.stream.vote;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class VoteRecordingSinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteRecordingSinkApplication.class, args);
	}
}
