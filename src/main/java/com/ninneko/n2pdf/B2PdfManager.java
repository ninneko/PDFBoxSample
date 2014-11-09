package com.ninneko.n2pdf;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Queue;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * @author ninneko
 */
public class B2PdfManager {
    private Queue<B2PdfComponent> components;
    private PDFont font;

    public B2PdfManager(PDFont font) {
        components = new ArrayDeque<B2PdfComponent>();
        this.font = font;
    }

    public void add(B2PdfComponent component) {
        components.add(component);
    }

    public File draw(String filePath) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream cStream = new PDPageContentStream(document, page);

            float margin = 10;
            float tableWidth = page.findMediaBox().getWidth() - (2 * margin);
            float top = page.findMediaBox().getHeight() - (2 * margin);
            float yCoordinate = top - 20f;
            for (B2PdfComponent component = components.poll(); component != null; component = components.poll()) {
                component.draw(cStream, 10, yCoordinate);
            }
            // Close Stream and save pdf
            cStream.close();
            // Save the document
            File file = new File(filePath);
            OutputStream oStream = new FileOutputStream(file);
            document.save(oStream);
            oStream.close();
            document.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
            return null;
    }

    public File createTestPdf(String text, String filePath) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // ここからテーブルテスト
            float Margin = 10;
            float tableWidth = page.findMediaBox().getWidth() - (2 * Margin);
            float top = page.findMediaBox().getHeight() - (2 * Margin);
            B2PdfTable table = new B2PdfTable();

            // 一行目
            PDPageContentStream cStream = new PDPageContentStream(document, page);
            B2PdfRow headerrow = new B2PdfRow(15f);
            B2PdfCell cell = new B2PdfCell(tableWidth, "test");
            cell.setFont(font);
            cell.setFillColor(Color.BLACK);
            cell.setTextColor(Color.WHITE);
            headerrow.addCell(cell);
            table.addRow(headerrow);

            // 二行目
            B2PdfRow row = new B2PdfRow(10f);
            cell = new B2PdfCell((tableWidth / 4), "これはテスト用の出力です");
            cell.setFont(font);
            cell.setFontSize(6);
            row.addCell(cell);
            cell = new B2PdfCell((tableWidth / 4) * 3, text);
            cell.setFont(font);
            cell.setFontSize(6);
            row.addCell(cell);
            table.addRow(row);

            float yCoordinate = top - 20f;
            while (!table.isEmpty()) {
                yCoordinate = table.draw(cStream, 0, yCoordinate);
            }
            // Close Stream and save pdf
            cStream.close();
            // Save the document
            File file = new File(filePath);
            OutputStream oStream = new FileOutputStream(file);
            document.save(oStream);
            oStream.close();
            document.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
        return null;
    }

}
