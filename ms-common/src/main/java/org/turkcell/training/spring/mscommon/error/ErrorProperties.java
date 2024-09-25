package org.turkcell.training.spring.mscommon.error;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "turkcell.error")
@Data
public class ErrorProperties {

    private String microservice;
    private String boundedContext;

}
