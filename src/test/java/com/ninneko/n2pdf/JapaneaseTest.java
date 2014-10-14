package com.ninneko.n2pdf;

import java.io.IOException;

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

public class JapaneaseTest extends TestCase {

    public void test_japanese() {

        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream pdfStream = new PDPageContentStream(document, page);

            pdfStream.beginText();

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

            //フォントとフォントサイズを設定
            pdfStream.setFont(pdFont, 36);

            //文字の配置設定
            pdfStream.moveTextPositionByAmount(50, 300);

            pdfStream.drawString(new String("日本語テステスaaaaaaaaaaaaaaaｇｄｓｇｓｇｓｇｓｇｓｇｓ風雨ううううううううううううううううううううううううううううううううううう"
                    .getBytes("MS932"), "ISO8859-1"));

            pdfStream.endText();
            pdfStream.close();

            document.save("target/hogehoge.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
    }

}
