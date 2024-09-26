package org.turkcell.training.spring.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsRestaurantApplication.class,
                              args);
    }

}
