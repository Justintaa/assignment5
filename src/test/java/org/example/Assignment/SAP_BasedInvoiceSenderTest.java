package org.example.Assignment;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class SAP_BasedInvoiceSenderTest {

    @Test
    public void testWhenLowInvoicesSent() {
        // Mock the FilterInvoice class
        FilterInvoice mockFilter = mock(FilterInvoice.class);

        // Define the stubbed return value for low-value invoices
        List<Invoice> lowValueInvoices = Arrays.asList(
                new Invoice("Invoice1", 50),   // Value < 100
                new Invoice("Invoice3", 80)    // Value < 100
        );

        // Stub the method lowValueInvoices to return low-value invoices
        when(mockFilter.lowValueInvoices()).thenReturn(lowValueInvoices);

        // Mock the SAP class
        SAP mockSap = mock(SAP.class);

        // Create the object under test (passing the mocked filter and SAP sender)
        SAP_BasedInvoiceSender sapBasedInvoiceSender = new SAP_BasedInvoiceSender(mockFilter, mockSap);

        // Call the method under test
        sapBasedInvoiceSender.sendLowValuedInvoices();

        // Verify that the send() method was called
        for (Invoice invoice : lowValueInvoices) {
            verify(mockSap).send(invoice);  
        }
    }

    @Test
    public void testWhenNoInvoices() {
        // Mock the FilterInvoice class
        FilterInvoice mockFilter = mock(FilterInvoice.class);

        // Define the stubbed return value
        List<Invoice> noLowValueInvoices = Arrays.asList(
                new Invoice("Invoice1", 150),  // Value > 100
                new Invoice("Invoice2", 120)   // Value > 100
        );

        // Stub the method lowValueInvoices
        when(mockFilter.lowValueInvoices()).thenReturn(noLowValueInvoices);

        // Mock the SAP class
        SAP mockSap = mock(SAP.class);

        // Create the object under test
        SAP_BasedInvoiceSender sapBasedInvoiceSender = new SAP_BasedInvoiceSender(mockFilter, mockSap);

        // Call the method under test
        sapBasedInvoiceSender.sendLowValuedInvoices();

        // Verify that the send() method was NOT called
        verify(mockSap, never()).send(any(Invoice.class));
    }
}
