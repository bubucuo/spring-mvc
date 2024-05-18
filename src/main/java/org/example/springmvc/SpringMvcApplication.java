package org.example.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

//Spring bean 的两种声明方式
//1. xml
//2. 注解 (DataSourceConfig)
@ImportResource("classpath:context.xml")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

}
