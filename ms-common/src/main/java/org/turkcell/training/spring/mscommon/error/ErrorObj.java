package org.turkcell.training.spring.mscommon.error;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ErrorObj {
    private List<ErrorObj> subErrors;
    private String         microservice;
    private String         boundedContext;
    private String         errorDesc;
    private Integer        errorCode;

    public ErrorObj() {
    }

    @Builder(setterPrefix = "with")
    public ErrorObj(final List<ErrorObj> subErrorsParam,
                    final String microserviceParam,
                    final String boundedContextParam,
                    final String errorDescParam,
                    final Integer errorCodeParam) {
        subErrors      = subErrorsParam;
        microservice   = microserviceParam;
        boundedContext = boundedContextParam;
        errorDesc      = errorDescParam;
        errorCode      = errorCodeParam;
    }

    public static void main(String[] args) {
        ErrorObj errorObjLoc = new ErrorObj();
        errorObjLoc.setErrorDesc("etst");
        errorObjLoc.setErrorCode(10);

        ErrorObj errorObjLoc2 = new ErrorObj(null,
                                             "mc1",
                                             "b1",
                                             "error1",
                                             10);

        ErrorObj errorObjLoc3 = ErrorObj.builder()
                                        .withErrorDescParam("error1")
                                        .withErrorCodeParam(10)
                                        .withMicroserviceParam("ms1")
                                        .withBoundedContextParam("b1")
                                        .build();

    }
}

