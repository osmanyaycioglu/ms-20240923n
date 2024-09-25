package org.turkcell.training.spring.mscommon.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorConfig {

    @Bean
    public ErrorProperties errorProperties(){
        return new ErrorProperties();
    }


    @Bean
    public ErrorAdvice errorAdvice(){
        return new ErrorAdvice(errorProperties());
    }

}
