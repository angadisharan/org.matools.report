package org.matools.report;

import org.junit.jupiter.api.Test;
import org.matools.report.generator.InvoicePdfGenerator;
import org.matools.report.model.Invoice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceGeneratorTest {

    @Test
    void shouldGeneratePdfByte() {

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

    @Test
    void shouldGeneratePdf() throws IOException {

        Invoice invoice = Invoice.builder()
                .invoiceNumber("INV-001")
                .customerName("Sharan")
                .totalAmount(500)
                .build();

        InvoicePdfGenerator generator = new InvoicePdfGenerator();

        byte[] pdf = generator.generate(invoice);

        assertNotNull(pdf);
        assertTrue(pdf.length > 0);

        // ✅ Save to file
        Path path = Paths.get("invoice-test.pdf");

        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            fos.write(pdf);
        }

        System.out.println("PDF saved at: " + path.toAbsolutePath());
    }
}