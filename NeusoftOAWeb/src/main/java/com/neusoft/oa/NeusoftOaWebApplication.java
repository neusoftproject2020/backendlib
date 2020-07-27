package com.neusoft.oa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages={"com.neusoft.oa.hr.mapper","com.neusoft.oa.admin.mapper"})
public class NeusoftOaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeusoftOaWebApplication.class, args);
	}

}
