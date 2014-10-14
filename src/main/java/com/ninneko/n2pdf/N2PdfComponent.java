package com.ninneko.n2pdf;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;

public abstract class N2PdfComponent extends N2PdfContent {
    protected PDPage page;
    protected PDPageContentStream cStream;

    public abstract float drow(PDPageContentStream cStream, float Ypos) throws IOException;
}
