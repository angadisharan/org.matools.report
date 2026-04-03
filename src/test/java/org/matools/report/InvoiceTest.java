package org.matools.report;

import org.junit.jupiter.api.Test;
import org.matools.report.model.Invoice;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    @Test
    void shouldCreateInvoiceUsingBuilder() {

        Invoice invoice = Invoice.builder()
                .invoiceNumber("INV-001")
                .totalAmount(new BigDecimal(600))
                .build();

        assertNotNull(invoice);
        assertEquals("INV-001", invoice.getInvoiceNumber());
        assertEquals(500, invoice.getTotalAmount());
    }
}