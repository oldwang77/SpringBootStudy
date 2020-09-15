package com.kuang.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 程序的主入口
// 标注这是一个SpringBootApplication程序
@SpringBootApplication
public class HelloworldApplication {

	public static void main(String[] args) {
		// SpringApplication
		// 将springboot应用启动
		SpringApplication.run(HelloworldApplication.class, args);
	}
}

