package org.example.Assignment;

import java.util.ArrayList;
import java.util.List;

public class SAP_BasedInvoiceSender {

    private final FilterInvoice filter;
    private final SAP sap;

    public SAP_BasedInvoiceSender(FilterInvoice filter, SAP sap) {
        this.filter = filter;
        this.sap = sap;
    }

    // Modify the sendLowValuedInvoices method to handle exceptions
    public List<Invoice> sendLowValuedInvoices() {
        List<Invoice> lowValuedInvoices = filter.lowValueInvoices();
        List<Invoice> failedValueInvoices = new ArrayList<>();

        for (Invoice invoice : lowValuedInvoices) {
            try {
                sap.send(invoice);  // Try to send the invoice
            } catch (FailToSendSAPInvoiceException e) {
                // If an exception occurs, add the invoice to the failed list
                failedValueInvoices.add(invoice);
            }
        }

        return failedValueInvoices;  // Return the list of failed invoices
    }
}

