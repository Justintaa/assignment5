package org.example.Assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class FilterInvoicesStubbedTest {

    @Test
    public void filterInvoicesStubbedTest() {
        // Create a mock of QueryInvoicesDAO
        QueryInvoicesDAO mockDAO = mock(QueryInvoicesDAO.class);

        // Define the stubbed behavior for the mock DAO
        List<Invoice> stubInvoices = Arrays.asList(
                new Invoice("Invoice1", 50),
                new Invoice("Invoice2", 120),
                new Invoice("Invoice3", 80)
        );
        when(mockDAO.all()).thenReturn(stubInvoices);

        // Pass the mock DAO to the FilterInvoice constructor
        FilterInvoice filterInvoice = new FilterInvoice(mockDAO);

        // Run the method and check the results
        List<Invoice> lowValueInvoices = filterInvoice.lowValueInvoices();

        // Assert that only invoices with value < 100 are returned
        assertEquals(2, lowValueInvoices.size());
        assertTrue(lowValueInvoices.stream().allMatch(invoice -> invoice.getValue() < 100));
    }
}
