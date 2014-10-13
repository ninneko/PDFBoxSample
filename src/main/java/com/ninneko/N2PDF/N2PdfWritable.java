package com.ninneko.N2PDF;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;

public abstract class N2PdfWritable extends N2PdfComponent {
	protected PDPage page;
	protected PDPageContentStream cStream;
}
