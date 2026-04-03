package org.matools.report;

import org.junit.jupiter.api.Test;
import org.matools.report.generator.InvoicePdfGenerator;
import org.matools.report.model.Invoice;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceGeneratorTest {

    @Test
    void shouldGeneratePdf() {

        Invoice invoice = Invoice.builder()
                .invoiceNumber("INV-001")
                .customerName("Sharan")
                .totalAmount(500)
                .build();

        InvoicePdfGenerator generator = new InvoicePdfGenerator();

        byte[] pdf = generator.generate(invoice);

        assertNotNull(pdf);
        assertTrue(pdf.length > 0);
    }
}