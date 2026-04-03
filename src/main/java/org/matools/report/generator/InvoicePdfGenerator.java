package org.matools.report.generator;

import org.matools.report.model.Invoice;

public class InvoicePdfGenerator implements ReportGenerator<Invoice> {

    private final PdfGenerator pdfGenerator = new PdfGenerator();

    @Override
    public byte[] generate(Invoice invoice) {
        return pdfGenerator.generate(invoice);
    }
}