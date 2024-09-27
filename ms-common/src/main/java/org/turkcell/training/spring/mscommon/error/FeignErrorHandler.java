package org.turkcell.training.spring.mscommon.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;


public class FeignErrorHandler implements ErrorDecoder {

    @Override
    public FeignResponseException decode(final String sParam,
                                         final Response responseParam) {
        try {
            ObjectMapper objectMapperLoc = new ObjectMapper();
            ErrorObj errorObjLoc = objectMapperLoc.readValue(responseParam.body()
                                                                          .asInputStream(),
                                                             ErrorObj.class);
            return new FeignResponseException(errorObjLoc);
        } catch (Exception eParam) {
            return new FeignResponseException(null);
        }
    }
}
