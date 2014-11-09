package com.ninneko.n2pdf;

import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptorDictionary;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

/**
 * @author yohei.naruse
 */
public class B2PdfFactory {
    private PDFont font;
    private static B2PdfFactory instance;

    public static synchronized B2PdfFactory getInstance() {
        if (instance == null) {
            instance = new B2PdfFactory();
        }
        return instance;
    }

    public B2PdfFactory() {
        font = createFont();
    }

    public B2PdfManager createManager() {
        return new B2PdfManager(font);
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

    public PDFont getFont(){
        return font;
    }
}
