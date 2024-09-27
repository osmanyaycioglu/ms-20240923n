package org.turkcell.training.spring.order.integration.resiliency.test;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyApplicationRunner implements ApplicationRunner {
    private final CallMe        callMe;
    private final RetryRegistry retryRegistry;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        Retry         retryLoc   = retryRegistry.retry("restaurant-menu-get-price-feign");
        System.out.println(retryLoc.getRetryConfig().toString());
        for (int i = 0; i < 30; i++) {
            System.out.println("Calling XYZ : " + i);

            try {
                callMe.xyz(null);
            } catch (Exception eParam) {
                System.out.println("Exception : " + eParam.getClass()
                                                          .getName() + " msg : " + eParam.getMessage());
            }
            Retry.Metrics metricsLoc = retryLoc.getMetrics();
            System.out.println("Success-W : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
                               + " Success-WO : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                               + " Fail-W : "
                               + metricsLoc.getNumberOfFailedCallsWithRetryAttempt()
                               + " Fail-WO : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
            );
        }
    }
}
