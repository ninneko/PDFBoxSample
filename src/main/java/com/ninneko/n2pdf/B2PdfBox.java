package com.ninneko.n2pdf;

import java.util.List;

import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;

/**
 *
 * @author yohei.naruse
 */
public class B2PdfBox extends B2PdfComponent {

    private List<B2PdfContent> contents;

    public float drow(PDPageContentStream cStream, float Ypos) {
        // TODO
        return 0;
    }

    public void add(B2PdfContent content) {
        contents.add(content);
    }

    public float getHeight() {
        float maxHeight = 0;

        return maxHeight;
    }
}
