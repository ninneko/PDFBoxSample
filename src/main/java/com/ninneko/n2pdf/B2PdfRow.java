package com.ninneko.n2pdf;

import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ninneko
 */
public class B2PdfRow extends B2PdfContent {

    List<B2PdfCell> cells;
    float height;

    public B2PdfRow(List<B2PdfCell> cells, float height) {
        this.cells = cells;
        this.height = height;
    }

    public B2PdfRow(float height) {
        this.height = height;
    }

    public float draw(PDPageContentStream cStream, float xPos, float yPos) throws IOException {
        float x = marginLeft+xPos;
        float y = yPos;

        // 一番上の水平線
        cStream.drawLine(x, y, x + getWidth(), y);

        for (B2PdfCell cell : getCells()) {
            // 垂直線+色塗りのはじまり
            if (cell.getFillColor() != null) {
                cStream.setNonStrokingColor(cell.getFillColor());
                cStream.fillRect(x, y - getHeight(), cell.getWidth(), getHeight() - 1f);
                cStream.closeSubPath();
            }
            cStream.setNonStrokingColor(Color.BLACK);
            cStream.drawLine(x, y, x, y - getHeight());
            cStream.closeSubPath();
            x += cell.getWidth();
        }
        cStream.setNonStrokingColor(Color.BLACK);
        cStream.drawLine(x, y, x, y - getHeight());
        cStream.closeSubPath();
        // 垂直線+色塗りのおわり

        // 文字列書き込み開始
        x = marginLeft; // TODO マージン考える
        y = yPos - (getHeight() - B2PdfDef.DEFAULT_CELL_MARGIN); // TODO マージン考える
        for (B2PdfCell cell : getCells()) {
            cell.draw(cStream,x,y);
            x += cell.getWidth(); // TODO マージン考える
        }

        y = yPos - getHeight();
        return y;
    }

    public void addCell(B2PdfCell cell) {
        if (cells == null) {
            cells = new ArrayList<B2PdfCell>();
        }
        cells.add(cell);
    }

    public float getWidth() {
        float SumOfWidth = 0;
        for (B2PdfCell cell : cells) {
            SumOfWidth += cell.getWidth();
        }
        return SumOfWidth;
    }

    /**
     * cellsを取得します。
     * @return cells
     */
    public List<B2PdfCell> getCells() {
        return cells;
    }

    /**
     * cellsを設定します。
     * @param cells cells
     */
    public void setCells(List<B2PdfCell> cells) {
        this.cells = cells;
    }

    /**
     * heightを取得します。
     * @return height
     */
    public float getHeight() {
        return height;
    }

    /**
     * heightを設定します。
     * @param height height
     */
    public void setHeight(float height) {
        this.height = height;
    }
}
