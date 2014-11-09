package com.ninneko.n2pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

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

public class TableTest extends TestCase {
    public void test_table() {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream cStream = new PDPageContentStream(document, page);

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
            PDFont pdFont = new PDType0Font(font);
            // ここからテーブルテスト
            float Margin = 10;
            List<String[]> facts = getFacts();
            float tableWidth = page.findMediaBox().getWidth() - (2 * Margin);
            float top = page.findMediaBox().getHeight() - (2 * Margin);
            B2PdfTable table = new B2PdfTable();

            // 一行目
            B2PdfRow headerrow = new B2PdfRow(15f);
            B2PdfCell cell = new B2PdfCell(tableWidth, "test");
            cell.setFont(pdFont);
            cell.setFillColor(Color.BLACK);
            cell.setTextColor(Color.WHITE);
            headerrow.addCell(cell);
            table.addRow(headerrow);

            // 二行目
            B2PdfRow row = new B2PdfRow(10f);
            cell = new B2PdfCell((tableWidth / 4), "せりふ");
            cell.setFont(pdFont);
            cell.setFontSize(6);
            row.addCell(cell);
            cell = new B2PdfCell((tableWidth / 4), "人物");
            cell.setFont(pdFont);
            cell.setFontSize(6);
            row.addCell(cell);
            cell = new B2PdfCell((tableWidth / 4), "枝番");
            cell.setFont(pdFont);
            cell.setFontSize(6);
            row.addCell(cell);
            cell = new B2PdfCell((tableWidth / 4), "出典");
            cell.setFont(pdFont);
            cell.setFontSize(6);
            row.addCell(cell);
            table.addRow(row);

            // 中身
            for (String[] fact : facts) {

                row = new B2PdfRow(10f);
                cell = new B2PdfCell((tableWidth / 3) * 2, fact[0]);
                cell.setFont(pdFont);
                cell.setFontSize(6);
                row.addCell(cell);

                for (int i = 1; i < fact.length; i++) {
                    cell = new B2PdfCell(((tableWidth / 9)), fact[i]);
                    cell.setFont(pdFont);
                    cell.setFontSize(6);
                    // Set colors
                    if (fact[i].contains("beer"))
                        cell.setFillColor(Color.yellow);
                    if (fact[i].contains("champion"))
                        cell.setTextColor(Color.GREEN);
                    row.addCell(cell);

                }
                table.addRow(row);
            }

            float yCoordinate = top - 20f;
            while (!table.isEmpty()) {
//                yCoordinate = table.draw(cStream, yCoordinate);
            }
            // Close Stream and save pdf
            cStream.close();
            // Save the document
            document.save("aaaaaaa.pdf");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> getFacts() {
        List<String[]> facts = new ArrayList<String[]>();
        facts.add(new String[] { "お前のいる場所は", "列海王", "1", "範馬刃牙" });
        facts.add(new String[] { "我々中国拳法が2000年前に通過した！！！！", "列海王", "2", "範馬刃牙" });
        facts.add(new String[] { "ボクシングにはけり技がない", "範馬刃牙", "1", "範馬刃牙" });
        facts.add(new String[] { "そう思っていた時期が私にもありました", "範馬刃牙", "2", "範馬刃牙" });

        return facts;
    }
}
