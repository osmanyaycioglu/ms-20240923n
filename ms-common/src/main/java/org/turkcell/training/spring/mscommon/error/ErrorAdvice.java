package org.turkcell.training.spring.mscommon.error;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ErrorAdvice.class);

    private final ErrorProperties errorProperties;


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleError(IllegalArgumentException exp) {
        return ErrorObj.builder()
                       .withErrorDescParam(exp.getMessage())
                       .withErrorCodeParam(10001)
                       .withMicroserviceParam(errorProperties.getMicroservice())
                       .withBoundedContextParam(errorProperties.getBoundedContext())
                       .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleError(MethodArgumentNotValidException exp) {
        return ErrorObj.builder()
                       .withSubErrorsParam(generateSubErrors(exp))
                       .withErrorDescParam("validation error")
                       .withErrorCodeParam(10002)
                       .withMicroserviceParam(errorProperties.getMicroservice())
                       .withBoundedContextParam(errorProperties.getBoundedContext())
                       .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handleError(Exception exp) {
        logger.error("[ErrorAdvice][generateSubErrors]-> *Error* : " + exp.getMessage(),
                     exp);
        if (exp instanceof NullPointerException) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                                 .header("traceId",
                                         UUID.randomUUID()
                                             .toString())
                                 .body(ErrorObj.builder()
                                               .withErrorDescParam(exp.getMessage())
                                               .withErrorCodeParam(50005)
                                               .withMicroserviceParam(errorProperties.getMicroservice())
                                               .withBoundedContextParam(errorProperties.getBoundedContext())
                                               .build());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .header("traceId",
                                     UUID.randomUUID()
                                         .toString())
                             .body(ErrorObj.builder()
                                           .withErrorDescParam(exp.getMessage())
                                           .withErrorCodeParam(50005)
                                           .withMicroserviceParam(errorProperties.getMicroservice())
                                           .withBoundedContextParam(errorProperties.getBoundedContext())
                                           .build());
    }


    private List<ErrorObj> generateSubErrors(MethodArgumentNotValidException exp) {
        return exp.getAllErrors()
                  .stream()
                  .map(se -> ErrorObj.builder()
                                     .withErrorDescParam("" + se)
                                     .withErrorCodeParam(10003)
                                     .withMicroserviceParam(errorProperties.getMicroservice())
                                     .withBoundedContextParam(errorProperties.getBoundedContext())
                                     .build())
                  .collect(Collectors.toList());
    }
}
