package com.ninneko.n2pdf;

import java.awt.Color;

import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 *
 * @author ninneko
 */
public class N2PdfCell extends N2PdfContent {
    float width;
    String text;
    private PDFont font;
    private float fontSize = N2PdfDef.DEFAULT_FONT_SIZE;
    private Color fillColor;
    private Color textColor = N2PdfDef.DEFAULT_COLOR;
    private float marginVertical = N2PdfDef.DEFAULT_CELL_MARGIN;
    private float marginHorizontal = N2PdfDef.DEFAULT_CELL_MARGIN;

    public N2PdfCell(float width, String text) {
        this.width = width;
        this.text = text;

    }

    public N2PdfParagraph getParagraph() {
        return new N2PdfParagraph(text, font, (int) fontSize, (int) width);
    }

    /**
     * widthを取得します。
     * @return width
     */
    public float getWidth() {
        return width;
    }

    /**
     * widthを設定します。
     * @param width width
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * textを取得します。
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * textを設定します。
     * @param text text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * fontを取得します。
     * @return font
     */
    public PDFont getFont() {
        return font;
    }

    /**
     * fontを設定します。
     * @param font font
     */
    public void setFont(PDFont font) {
        this.font = font;
    }

    /**
     * fontSizeを取得します。
     * @return fontSize
     */
    public float getFontSize() {
        return fontSize;
    }

    /**
     * fontSizeを設定します。
     * @param fontSize fontSize
     */
    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * fillColorを取得します。
     * @return fillColor
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * fillColorを設定します。
     * @param fillColor fillColor
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * textColorを取得します。
     * @return textColor
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * textColorを設定します。
     * @param textColor textColor
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     * marginVerticalを取得します。
     * @return marginVertical
     */
    public float getMarginVertical() {
        return marginVertical;
    }

    /**
     * marginVerticalを設定します。
     * @param marginVertical marginVertical
     */
    public void setMarginVertical(float marginVertical) {
        this.marginVertical = marginVertical;
    }

    /**
     * marginHorizontalを取得します。
     * @return marginHorizontal
     */
    public float getMarginHorizontal() {
        return marginHorizontal;
    }

    /**
     * marginHorizontalを設定します。
     * @param marginHorizontal marginHorizontal
     */
    public void setMarginHorizontal(float marginHorizontal) {
        this.marginHorizontal = marginHorizontal;
    }

}
