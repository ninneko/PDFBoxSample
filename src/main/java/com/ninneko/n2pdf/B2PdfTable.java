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
    private float marginTop = 10;
    private float marginBottom = 10;
    private float marginLeft = 10;
    private float marginRight = 10;
    private Queue<B2PdfRow> rows;

    public float drow(PDPageContentStream cStream, float yCoordinate) throws IOException {
        float x = marginLeft;
        float y = yCoordinate;
        if (rows.isEmpty()) {
            return y;
        }
        B2PdfRow row = rows.poll();

        // 一番上の水平線
        cStream.drawLine(x, y, x + row.getWidth(), y);

        for (B2PdfCell cell : row.getCells()) {
            // 垂直線+色塗りのはじまり
            if (cell.getFillColor() != null) {
                cStream.setNonStrokingColor(cell.getFillColor());
                cStream.fillRect(x, y - row.getHeight(), cell.getWidth(), row.getHeight() - 1f);
                cStream.closeSubPath();
            }
            cStream.setNonStrokingColor(Color.BLACK);
            cStream.drawLine(x, y, x, y - row.getHeight());
            cStream.closeSubPath();
            x += cell.getWidth();
        }
        cStream.setNonStrokingColor(Color.BLACK);
        cStream.drawLine(x, y, x, y - row.getHeight());
        cStream.closeSubPath();
        // 垂直線+色塗りのおわり

        // 文字列書き込み開始
        x = marginLeft; // TODO マージン考える
        y = yCoordinate - (row.getHeight() - B2PdfDef.DEFAULT_CELL_MARGIN); // TODO マージン考える
        for (B2PdfCell cell : row.getCells()) {
            cStream.setFont(cell.getFont(), cell.getFontSize());
            cStream.setNonStrokingColor(cell.getTextColor());
            cStream.beginText();
            cStream.moveTextPositionByAmount(x, y);
            List<String> lines = cell.getParagraph().getLines();
            int lineNum = lines.size();
            cStream.appendRawCommands(cell.getParagraph().getFontHeight() + " TL\n");
            for (String line : lines) {
                cStream.drawString(new String(line.getBytes("MS932"), "ISO8859-1"));
                if (lineNum > 0) {
                    cStream.appendRawCommands("T*\n");
                }
                lineNum--;
            }
            cStream.endText();
            cStream.closeSubPath();
            x += cell.getWidth(); // TODO マージン考える
        }

        y = yCoordinate - row.getHeight();
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
