package com.ninneko.N2PDF;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ninneko
 */
public class N2PdfRow extends N2PdfComponent {

	List<N2PdfCell> cellList;
	float height;

	public N2PdfRow(List<N2PdfCell> cells, float height) {
		this.cellList = cells;
		this.height = height;
	}

	public void addCell(N2PdfCell cell) {
		if (cellList == null) {
			cellList = new ArrayList<N2PdfCell>();
		}
		cellList.add(cell);
	}

	/**
	 * cellListを取得します。
	 * 
	 * @return cellList
	 */
	public List<N2PdfCell> getCellList() {
		return cellList;
	}

	/**
	 * cellListを設定します。
	 * 
	 * @param cellList
	 *            cellList
	 */
	public void setCellList(List<N2PdfCell> cellList) {
		this.cellList = cellList;
	}

	/**
	 * heightを取得します。
	 * 
	 * @return height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * heightを設定します。
	 * 
	 * @param height
	 *            height
	 */
	public void setHeight(float height) {
		this.height = height;
	}
}
