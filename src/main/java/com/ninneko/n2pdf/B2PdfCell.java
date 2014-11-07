package com.ninneko.n2pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 *
 * @author ninneko
 */
public class B2PdfCell extends B2PdfContent {
    float width;
    String text;
    private PDFont font;
    private float fontSize = B2PdfDef.DEFAULT_FONT_SIZE;
    private Color fillColor;
    private Color textColor = B2PdfDef.DEFAULT_COLOR;
    private float marginVertical = B2PdfDef.DEFAULT_CELL_MARGIN;
    private float marginHorizontal = B2PdfDef.DEFAULT_CELL_MARGIN;

    public B2PdfCell(float width, String text) {
        this.width = width;
        this.text = text;

    }

    public float drow(PDPageContentStream cStream, float xPos, float yPos) throws IOException {
        cStream.setFont(getFont(), getFontSize());
        cStream.setNonStrokingColor(getTextColor());
        cStream.beginText();
        cStream.moveTextPositionByAmount(xPos, yPos);
        List<String> lines = getParagraph().getLines();
        int lineNum = lines.size();
        cStream.appendRawCommands(getParagraph().getFontHeight() + " TL\n");
        for (String line : lines) {
            cStream.drawString(new String(line.getBytes("MS932"), "ISO8859-1"));
            if (lineNum > 0) {
                cStream.appendRawCommands("T*\n");
            }
            lineNum--;
        }
        cStream.endText();
        cStream.closeSubPath();
        return 0;
    }

    public B2PdfParagraph getParagraph() {
        return new B2PdfParagraph(text, font, (int) fontSize, (int) width);
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
