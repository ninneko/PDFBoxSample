package com.ninneko.n2pdf;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Queue;

import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptorDictionary;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

/**
 *
 * @author ninneko
 */
public class N2PdfManager {
    private Queue<N2PdfComponent> components;
    private N2PdfTable testTable;
    private PDFont font;

    public N2PdfManager() {
        components = new ArrayDeque<N2PdfComponent>();
        testTable = new N2PdfTable();
        components.add(testTable);
        font = createFont();
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
            N2PdfTable table = new N2PdfTable();

            // 一行目
            PDPageContentStream cStream = new PDPageContentStream(document, page);
            N2PdfRow headerrow = new N2PdfRow(15f);
            N2PdfCell cell = new N2PdfCell(tableWidth, "test");
            cell.setFont(font);
            cell.setFillColor(Color.BLACK);
            cell.setTextColor(Color.WHITE);
            headerrow.addCell(cell);
            table.addRow(headerrow);

            // 二行目
            N2PdfRow row = new N2PdfRow(10f);
            cell = new N2PdfCell((tableWidth / 4), "これはテスト用の出力です");
            cell.setFont(font);
            cell.setFontSize(6);
            row.addCell(cell);
            cell = new N2PdfCell((tableWidth / 4) * 3, text);
            cell.setFont(font);
            cell.setFontSize(6);
            row.addCell(cell);
            table.addRow(row);

            float yCoordinate = top - 20f;
            while (!table.isEmpty()) {
                yCoordinate = table.drow(cStream, yCoordinate);
            }
            // Close Stream and save pdf
            cStream.close();
            // Save the document
            File file = new File(filePath);
            OutputStream oStream = new FileOutputStream(file);
            document.save(oStream);
            oStream.close();
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PDFont createFont() {
        COSDictionary systeminfo = new COSDictionary();
        systeminfo.setString(COSName.REGISTRY, "Adobe");
        systeminfo.setString(COSName.ORDERING, "Japan1");
        systeminfo.setInt(COSName.SUPPLEMENT, 6);

        //フォントディスクリプタ設定
        PDFontDescriptorDictionary fd = new PDFontDescriptorDictionary();
        //ソフトウェア側で用意されている日本語フォントを指定
        fd.setFontName("KozGoPr6N-Medium");
        fd.setFlags(4);
        fd.setFontBoundingBox(new PDRectangle(new BoundingBox(-500, -300, 1200, 1400)));
        fd.setItalicAngle(0);
        fd.setAscent(1400);
        fd.setDescent(-300);
        fd.setCapHeight(700);
        fd.setStemV(100);

        //CIDフォント設定
        COSDictionary cid = new COSDictionary();
        cid.setItem(COSName.TYPE, COSName.FONT);
        cid.setItem(COSName.SUBTYPE, COSName.CID_FONT_TYPE0);
        cid.setItem(COSName.BASE_FONT, COSName.getPDFName("KozGoPr6N-Medium"));
        cid.setItem(COSName.CIDSYSTEMINFO, systeminfo);
        cid.setItem(COSName.FONT_DESC, fd);

        //フォント設定
        COSDictionary font = new COSDictionary();
        font.setItem(COSName.TYPE, COSName.FONT);
        font.setItem(COSName.SUBTYPE, COSName.TYPE0);
        font.setItem(COSName.BASE_FONT, COSName.getPDFName("KozGoPr6N-Medium"));
        font.setItem(COSName.ENCODING, COSName.ENCODING_90MS_RKSJ_H);

        COSArray array = new COSArray();
        array.add(cid);
        font.setItem(COSName.DESCENDANT_FONTS, array);

        //フォント作成
        return new PDType0Font(font);
    }
}
