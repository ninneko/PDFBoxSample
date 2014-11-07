package com.ninneko.n2pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;

/**
 *
 * @author ninneko
 */
public class B2PdfTable extends B2PdfComponent {
    private Queue<B2PdfRow> rows;

    public float drow(PDPageContentStream cStream, float xPos, float yCoordinate) throws IOException {
        float x = marginLeft;
        float y = yCoordinate;
        if (rows.isEmpty()) {
            return y;
        }
        B2PdfRow row = rows.poll();

        y = row.drow(cStream,xPos,yCoordinate);

        if (rows.isEmpty()) {
            cStream.drawLine(marginLeft, y, marginLeft + row.getWidth(), y);
            cStream.closeSubPath();
        }
        return y;
    }

    public void addRow(B2PdfRow row) {
        if (rows == null) {
            rows = new ArrayDeque<B2PdfRow>();
        }
        rows.add(row);
    }

    public boolean isEmpty() {
        return rows.isEmpty();
    }

    /**
     * marginTopを取得します。
     * @return marginTop
     */
    public float getMarginTop() {
        return marginTop;
    }

    /**
     * marginTopを設定します。
     * @param marginTop marginTop
     */
    public void setMarginTop(float marginTop) {
        this.marginTop = marginTop;
    }

    /**
     * marginBottomを取得します。
     * @return marginBottom
     */
    public float getMarginBottom() {
        return marginBottom;
    }

    /**
     * marginBottomを設定します。
     * @param marginBottom marginBottom
     */
    public void setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
    }

    /**
     * marginLeftを取得します。
     * @return marginLeft
     */
    public float getMarginLeft() {
        return marginLeft;
    }

    /**
     * marginLeftを設定します。
     * @param marginLeft marginLeft
     */
    public void setMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
    }

    /**
     * marginRightを取得します。
     * @return marginRight
     */
    public float getMarginRight() {
        return marginRight;
    }

    /**
     * marginRightを設定します。
     * @param marginRight marginRight
     */
    public void setMarginRight(float marginRight) {
        this.marginRight = marginRight;
    }

    /**
     * rowsを取得します。
     * @return rows
     */
    public Queue<B2PdfRow> getRows() {
        return rows;
    }

    /**
     * rowsを設定します。
     * @param rows rows
     */
    public void setRows(Queue<B2PdfRow> rows) {
        this.rows = rows;
    }

}
