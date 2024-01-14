package org.openifood.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.openifood.dto.authentication.response.BusinessErrorResponse;

@EqualsAndHashCode(callSuper = true)
@Value
public class IFoodBusinessException extends RuntimeException {

    BusinessErrorResponse response;

    public IFoodBusinessException(BusinessErrorResponse response) {
        super("A business error was occurred");
        this.response = response;
    }
}
