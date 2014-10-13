/*
 Quodlibet.be
 */
package com.ninneko.N2PDF;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * 
 * @author ninneko
 */
public class N2PdfParagraph extends N2PdfWritable {

	private String text;
	private int fontSize = N2PdfDef.DEFAULT_FONT_SIZE;
	private int width;
	private int color = N2PdfDef.DEFAULT_COLOR;
	private PDFont font = PDType1Font.HELVETICA;

	public N2PdfParagraph(String text, PDFont font, int fontSize, int width) {
		this.text = text;
		this.font = font;
		this.fontSize = fontSize;
		this.width = width;
	}

	private int[] createCandidateOfWrapPoint(String text) {
		String[] splits = text.split("(?<=\\W)");
		int[] candidateOfWrapPoint = new int[splits.length];

		candidateOfWrapPoint[0] = splits[0].length();
		for (int i = 1; i < splits.length; i++) {
			candidateOfWrapPoint[i] = candidateOfWrapPoint[i - 1]
					+ splits[i].length();
		}

		return candidateOfWrapPoint;
	}

	public List<String> getLineList() throws IOException {
		List<String> result = new ArrayList<String>();
		int[] candidateOfWrapPoint = createCandidateOfWrapPoint(text);

		int start = 0;
		int last = 0;
		for (int end : candidateOfWrapPoint) {
			float width = font.getStringWidth(text.substring(start, end))
					/ N2PdfDef.UNIT_SIZE * fontSize;
			if (start < last && width > this.width) {
				result.add(text.substring(start, last));
				start = last;
			}
			last = end;
		}

		result.add(text.substring(start));
		return result;
	}

	public float getFontHeight() {
		return font.getFontDescriptor().getFontBoundingBox().getHeight()
				/ N2PdfDef.UNIT_SIZE * fontSize;

	}

	public float getFontWidth() {
		return font.getFontDescriptor().getFontBoundingBox().getWidth()
				/ N2PdfDef.UNIT_SIZE * fontSize;
	}

	/**
	 * fontSizeを設定します。
	 * 
	 * @param fontSize
	 *            fontSize
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * textを取得します。
	 * 
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * textを設定します。
	 * 
	 * @param text
	 *            text
	 */
	public void setText(String text) {
		this.text = text;
	}

	public int getFontSize() {
		return fontSize;
	}

	/**
	 * widthを取得します。
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * widthを設定します。
	 * 
	 * @param width
	 *            width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * colorを取得します。
	 * 
	 * @return color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * colorを設定します。
	 * 
	 * @param color
	 *            color
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * fontを取得します。
	 * 
	 * @return font
	 */
	public PDFont getFont() {
		return font;
	}

	/**
	 * fontを設定します。
	 * 
	 * @param font
	 *            font
	 */
	public void setFont(PDFont font) {
		this.font = font;
	}
}
