package org.example.Assignment;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SAP_BasedInvoiceSenderTest {

    @Test
    public void testThrowExceptionWhenBadInvoice() {
        // Create mock objects for FilterInvoice and SAP
        FilterInvoice mockFilter = mock(FilterInvoice.class);
        SAP mockSAP = mock(SAP.class);

        // Set up the mock behavior to return a list of invoices
        Invoice invoice1 = new Invoice("Customer A", 50);
        Invoice invoice2 = new Invoice("Customer B", 200);
        List<Invoice> mockInvoices = List.of(invoice1, invoice2);

        // When lowValueInvoices is called, return the mock list
        when(mockFilter.lowValueInvoices()).thenReturn(mockInvoices);

        // Simulate an exception being thrown for the first invoice
        doThrow(new FailToSendSAPInvoiceException("Failed to send invoice")).when(mockSAP).send(invoice1);

        // Create the system under test
        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(mockFilter, mockSAP);

        // Call the method to test
        List<Invoice> failedInvoices = sender.sendLowValuedInvoices();

        // Verify that send was called on the mock SAP object
        verify(mockSAP, times(1)).send(invoice1);
        verify(mockSAP, times(1)).send(invoice2);

        // Assert that the failedInvoices list contains the invoice that failed
        assertEquals(1, failedInvoices.size());
        assertTrue(failedInvoices.contains(invoice1));
    }
}
