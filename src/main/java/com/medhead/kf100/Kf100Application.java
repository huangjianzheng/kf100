package com.medhead.kf100;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.medhead.kf100.conf.shiro.ShiroProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({ShiroProperties.class})
@MapperScan("com.medhead.kf100.mapper")
@EnableCaching
public class Kf100Application {

	public static void main(String[] args) {
		SpringApplication.run(Kf100Application.class, args);
	}

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		return paginationInterceptor;
	}
}
