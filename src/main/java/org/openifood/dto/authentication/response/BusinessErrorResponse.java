package org.openifood.dto.authentication.response;

import lombok.Value;

import java.util.List;

@Value
public class BusinessErrorResponse {
    /**
     * Business error code (e.g. IAM-2000, IAM-2001)
     *
     * @todo map all error codes
     */
    String code;
    String key;
    String message;
    String localizedMessage;
    List<String> details;
}
