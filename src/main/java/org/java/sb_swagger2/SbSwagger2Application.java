package org.java.sb_swagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbSwagger2Application {

    public static void main(String[] args) {
        SpringApplication.run(SbSwagger2Application.class, args);
        System.out.println("Swagger访问地址为：http://localhost:9090/swagger-ui.html");
    }

}
