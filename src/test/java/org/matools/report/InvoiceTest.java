package org.matools.report;

import org.junit.jupiter.api.Test;
import org.matools.report.model.Invoice;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    @Test
    void shouldCreateInvoiceUsingBuilder() {

        Invoice invoice = Invoice.builder()
                .invoiceNumber("INV-001")
                .customerName("Sharan")
                .totalAmount(500)
                .build();

        assertNotNull(invoice);
        assertEquals("INV-001", invoice.getInvoiceNumber());
        assertEquals(500, invoice.getTotalAmount());
    }
}