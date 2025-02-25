package org.example.Assignment;

public class FailToSendSAPInvoiceException extends RuntimeException {

    public FailToSendSAPInvoiceException(String message) {
        super(message);
    }

    public FailToSendSAPInvoiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
