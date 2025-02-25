package org.example.Assignment;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterInvoiceTest {

    @Test
    public void filterInvoiceTest() {
        // Create a real DAO and pass it to the FilterInvoice class
        QueryInvoicesDAO realDao = new QueryInvoicesDAO(new Database());  // Assuming real DAO will access the actual database
        FilterInvoice filterInvoice = new FilterInvoice(realDao);

        // Run the test to fetch low-value invoices
        List<Invoice> lowValueInvoices = filterInvoice.lowValueInvoices();

        // Check that the result is correct, you can adjust this based on your actual data in the database
        assertTrue(lowValueInvoices.size() > 0);
    }
}
