package org.turkcell.training.spring.order.integration.resiliency.test;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class MyApplicationRunner implements ApplicationRunner {
    private final CallMe                 callMe;
    private final RetryRegistry          retryRegistry;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        Retry          retryLoc          = retryRegistry.retry("restaurant-menu-get-price-feign");
        CircuitBreaker circuitBreakerLoc = circuitBreakerRegistry.circuitBreaker("restaurant-menu-get-price-cb");
        System.out.println("****** RETRY CONFIG " + retryLoc.getRetryConfig()
                                                            .toString());
        System.out.println("****** CB CONFIG " + circuitBreakerLoc.getCircuitBreakerConfig()
                                                                  .toString());
        retryLoc.getEventPublisher()
                .onRetry(ec -> System.out.println("Retried : " + ec.toString()))
                .onError(ec -> System.out.println("Retry Error : " + ec.toString()));
        for (int i = 0; i < 100; i++) {
            System.out.println("Calling XYZ : " + i);

            try {
                Thread.sleep(200);
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
            CircuitBreaker.Metrics ccm = circuitBreakerLoc.getMetrics();
            System.out.println("index : "
                               + i
                               + " state : "
                               + circuitBreakerLoc.getState()
                               + " ratio : "
                               + ccm.getFailureRate()
                               + " s : "
                               + ccm.getNumberOfSuccessfulCalls()
                               + " f : "
                               + ccm.getNumberOfFailedCalls()
                               + " np : "
                               + ccm.getNumberOfNotPermittedCalls()
            );
        }
    }
}
