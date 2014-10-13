package com.ninneko.N2PDF;

import java.util.List;

/**
 * 
 * @author ninneko
 */
public class N2PdfTable extends N2PdfWritable {
	private float margin;
	private N2PdfRow headerRow;
	private List<N2PdfRow> rowList;

	/**
	 * marginを取得します。
	 * 
	 * @return margin
	 */
	public float getMargin() {
		return margin;
	}

	/**
	 * marginを設定します。
	 * 
	 * @param margin
	 *            margin
	 */
	public void setMargin(float margin) {
		this.margin = margin;
	}

	/**
	 * headerRowを取得します。
	 * 
	 * @return headerRow
	 */
	public N2PdfRow getHeaderRow() {
		return headerRow;
	}

	/**
	 * headerRowを設定します。
	 * 
	 * @param headerRow
	 *            headerRow
	 */
	public void setHeaderRow(N2PdfRow headerRow) {
		this.headerRow = headerRow;
	}

	/**
	 * rowListを取得します。
	 * 
	 * @return rowList
	 */
	public List<N2PdfRow> getRowList() {
		return rowList;
	}

	/**
	 * rowListを設定します。
	 * 
	 * @param rowList
	 *            rowList
	 */
	public void setRowList(List<N2PdfRow> rowList) {
		this.rowList = rowList;
	}

}
