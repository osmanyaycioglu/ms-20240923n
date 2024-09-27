package org.turkcell.training.spring.order.integration.resiliency;

import org.springframework.web.client.RestClientResponseException;
import org.turkcell.training.spring.mscommon.error.ErrorObj;
import org.turkcell.training.spring.mscommon.error.FeignResponseException;

import java.util.function.Predicate;

public class RestaurantMenuGetPriceRetryChooser implements Predicate<Exception> {

    @Override
    public boolean test(final Exception throwableParam) {
        if (throwableParam instanceof RestClientResponseException) {
            RestClientResponseException exceptionLoc = (RestClientResponseException) throwableParam;
            ErrorObj                    errorLoc     = exceptionLoc.getResponseBodyAs(ErrorObj.class);
            return check(errorLoc);
        } else if (throwableParam instanceof FeignResponseException) {
            FeignResponseException exceptionLoc = (FeignResponseException) throwableParam;
            ErrorObj               errorObjLoc  = exceptionLoc.getErrorObj();
            return check(errorObjLoc);
        }
        return true;
    }

    private static boolean check(final ErrorObj errorLoc) {
        switch (errorLoc.getErrorCode()) {
            case 1024:
                return true;
            default:
                return false;

        }
    }
}
