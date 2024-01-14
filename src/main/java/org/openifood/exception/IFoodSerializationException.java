package org.openifood.exception;

public class IFoodSerializationException extends RuntimeException {

    public IFoodSerializationException(Throwable exception) {
        super("Error on serialize responses from API, please check the logs and open an issue",
                exception);
    }
}
