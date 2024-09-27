package org.turkcell.training.spring.mscommon.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeignResponseException extends RuntimeException {
    private ErrorObj errorObj;

}
