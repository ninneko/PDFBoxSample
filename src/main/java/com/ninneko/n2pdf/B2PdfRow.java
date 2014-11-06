package com.ninneko.n2pdf;

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
