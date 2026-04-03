package org.matools.report.generator;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.matools.report.model.Invoice;

import java.io.ByteArrayOutputStream;

public class PdfGenerator {

    public byte[] generate(Invoice invoice) {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();

            document.add(new Paragraph("INVOICE"));
            document.add(new Paragraph("Invoice No: " + invoice.getInvoiceNumber()));
            document.add(new Paragraph("Customer: " + invoice.getCustomerName()));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.addCell("Item");
            table.addCell("Qty");
            table.addCell("Price");
            table.addCell("Total");

            if (invoice.getItems() != null) {
                invoice.getItems().forEach(item -> {
                    table.addCell(item.getName());
                    table.addCell(String.valueOf(item.getQuantity()));
                    table.addCell(String.valueOf(item.getPrice()));
                    table.addCell(String.valueOf(item.getTotal()));
                });
            }

            document.add(table);
            document.add(new Paragraph("Total: " + invoice.getTotalAmount()));

            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }
    }
}