package org.turkcell.training.spring.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.turkcell.training.spring.mscommon.error.ErrorConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties
@Import(ErrorConfig.class)
public class MsOrderApplication {

    public static void main(String[] args) {
        Class<MsOrderApplication> msOrderApplicationClassLoc = MsOrderApplication.class;
        SpringApplication.run(MsOrderApplication.class,
                              args);
    }

}
