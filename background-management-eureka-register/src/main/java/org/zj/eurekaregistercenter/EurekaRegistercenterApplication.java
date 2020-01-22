package org.zj.eurekaregistercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaRegistercenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRegistercenterApplication.class, args);
    }

}
