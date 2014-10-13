package com.ninneko.N2PDF;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;

/**
 * 
 * @author ninneko
 */
public class N2PdfBox extends N2PdfComponent {
	private PDPage page;
	private PDPageContentStream contentStream;

	/**
	 * pageを取得します。
	 * 
	 * @return page
	 */
	public PDPage getPage() {
		return page;
	}

	/**
	 * pageを設定します。
	 * 
	 * @param page
	 *            page
	 */
	public void setPage(PDPage page) {
		this.page = page;
	}

	/**
	 * contentStreamを取得します。
	 * 
	 * @return contentStream
	 */
	public PDPageContentStream getContentStream() {
		return contentStream;
	}

	/**
	 * contentStreamを設定します。
	 * 
	 * @param contentStream
	 *            contentStream
	 */
	public void setContentStream(PDPageContentStream contentStream) {
		this.contentStream = contentStream;
	}
}
