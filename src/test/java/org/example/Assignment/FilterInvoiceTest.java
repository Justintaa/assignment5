package org.example.Assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class FilterInvoiceTest {

    // Integration Test (no mocks, using real database interaction)
    @Test
    void filterInvoiceTest() {
        // Create a real FilterInvoice instance
        FilterInvoice filterInvoice = new FilterInvoice(); // This will create a real QueryInvoicesDAO, which connects to the real database

        // Call the method under test
        List<Invoice> invoices = filterInvoice.lowValueInvoices();

        // Example assertions
        assertNotNull(invoices); // Ensure the result is not null
        assertTrue(invoices.size() > 0); // Assuming that there are low-value invoices in the database
    }
}
