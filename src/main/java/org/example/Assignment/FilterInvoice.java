package org.example.Assignment;

import java.util.List;
import static java.util.stream.Collectors.toList;

public class FilterInvoice {

    private QueryInvoicesDAO dao;

    // Constructor now accepts a QueryInvoicesDAO, allowing us to inject a stub or mock for testing
    public FilterInvoice(QueryInvoicesDAO dao) {
        this.dao = dao;
    }

    public List<Invoice> lowValueInvoices() {
        List<Invoice> all = dao.all();
        return all.stream()
                .filter(invoice -> invoice.getValue() < 100)
                .collect(toList());
    }
}