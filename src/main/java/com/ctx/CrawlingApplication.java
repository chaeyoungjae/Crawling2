package com.ctx;

import com.ctx.batch.QuartzScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ctx"})
@EnableAutoConfiguration
public class CrawlingApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrawlingApplication.class, args);
	}
}
