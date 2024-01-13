package ru.shefer;

public class UnacceptableDeliveryException extends RuntimeException {
    public UnacceptableDeliveryException() {
    }

    public UnacceptableDeliveryException(String message) {
        super(message);
    }

    public UnacceptableDeliveryException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnacceptableDeliveryException(Throwable cause) {
        super(cause);
    }

    public UnacceptableDeliveryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
