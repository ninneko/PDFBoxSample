package com.ninneko.n2pdf;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;

/**
 * Created by yohei.naruse on 2014/11/07.
 */
public class B2PdfLine extends B2PdfContent {

    protected float toXPos = 0;
    protected float toYPos = 0;

    @Override
    public float draw(PDPageContentStream cStream, float xPos, float yPos) throws IOException {
        cStream.drawLine(xPos, yPos, toXPos, toYPos);
        return 0;
    }
}
