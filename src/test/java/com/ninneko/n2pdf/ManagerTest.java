package com.ninneko.n2pdf;

import junit.framework.TestCase;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;

import java.awt.*;
import java.io.File;

public class ManagerTest extends TestCase {
    public void test_table() {
        B2PdfFactory factory = B2PdfFactory.getInstance();
        B2PdfManager manager = factory.createManager();
        B2PdfTable table = new B2PdfTable();

        // とりあえずテスト用にwidthとるためだけの部分
        PDPage page = new PDPage();
        float margin = 10;
        float tableWidth = page.findMediaBox().getWidth() - (2 * margin);

        // 一行目
        B2PdfRow headerrow = new B2PdfRow(15f);
        B2PdfCell cell = new B2PdfCell(tableWidth, "test");
        cell.setFont(factory.getFont());
//        cell.setFillColor(Color.BLACK);
//        cell.setTextColor(Color.WHITE);
        headerrow.addCell(cell);
        table.addRow(headerrow);

        // 二行目
        B2PdfRow row = new B2PdfRow(10f);
        cell = new B2PdfCell((tableWidth / 4), "これはテスト用の出力です");
        cell.setFont(factory.getFont());
        cell.setFontSize(6);
        row.addCell(cell);
        cell = new B2PdfCell((tableWidth / 4) * 3, "aaaaaaaaaaaaaaaaaaaaaaaaaa");
        cell.setFont(factory.getFont());
        cell.setFontSize(6);
        row.addCell(cell);
        table.addRow(row);
        manager.add(table);
        File file = manager.draw("tabletest.pdf");

    }

    public void test_box() {
        B2PdfFactory factory = B2PdfFactory.getInstance();
        B2PdfManager manager = factory.createManager();
        B2PdfTable table = new B2PdfTable();

        // とりあえずテスト用にwidthとるためだけの部分
        PDPage page = new PDPage();
        float margin = 10;
        float tableWidth = page.findMediaBox().getWidth() - (2 * margin);
        B2PdfParagraph paragraph = new B2PdfParagraph("段落てすとおおおおおおお", factory.getFont(), 15, 100);
        manager.add(paragraph);


        File file = manager.draw("boxtest.pdf");

    }
}
