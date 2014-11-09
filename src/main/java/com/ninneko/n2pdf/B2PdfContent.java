package com.ninneko.n2pdf;

import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;

import java.io.IOException;

public abstract class B2PdfContent {
    protected float marginTop = 10;
    protected float marginBottom = 10;
    protected float marginLeft = 10;
    protected float marginRight = 10;
    protected float xPos = 0;
    protected float yPos = 0;
    public abstract float draw(PDPageContentStream cStream, float xPos, float yPos) throws IOException;
}
