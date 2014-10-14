package com.ninneko.n2pdf;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ninneko
 */
public class N2PdfRow extends N2PdfContent {

    List<N2PdfCell> cells;
    float height;

    public N2PdfRow(List<N2PdfCell> cells, float height) {
        this.cells = cells;
        this.height = height;
    }

    public N2PdfRow(float height) {
        this.height = height;
    }

    public void addCell(N2PdfCell cell) {
        if (cells == null) {
            cells = new ArrayList<N2PdfCell>();
        }
        cells.add(cell);
    }

    public float getWidth() {
        float SumOfWidth = 0;
        for (N2PdfCell cell : cells) {
            SumOfWidth += cell.getWidth();
        }
        return SumOfWidth;
    }

    /**
     * cellsを取得します。
     * @return cells
     */
    public List<N2PdfCell> getCells() {
        return cells;
    }

    /**
     * cellsを設定します。
     * @param cells cells
     */
    public void setCells(List<N2PdfCell> cells) {
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
