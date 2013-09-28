package com.webimport.webtable;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class Table {
	private final HtmlTable htmlTable;
	private List<Row> rows = null;

	public Table(HtmlTable htmlTable) {
		this.htmlTable = htmlTable;
	}

	public List<Row> rows() {
		if (this.rows == null) {
			this.rows = new ArrayList<Row>();

			for (HtmlTableRow row : this.htmlTable.getRows()) {
				this.rows.add(new Row(row.getCells()));
			}
		}
		return this.rows;
	}

	public List<Row> rowsBetweenHeaders(String beginHead, String endHead) {
		List<Row> result = new ArrayList<Row>();
		for (Object row : this.htmlTable.getByXPath("//table/tbody/tr[th='" + beginHead + "']/following-sibling::tr[th='" + endHead
				+ "']/preceding-sibling::tr[preceding-sibling::tr[th='" + beginHead + "']]")) {
			result.add(new Row(((HtmlTableRow) row).getCells()));
		}

		return result;
	}

	public boolean rowExists(String cellData) {

		return getRowByCellData(cellData) != null;
	}

	public Row getRowByCellData(String cellData) {
		for (HtmlTableRow row : this.htmlTable.getRows()) {
			if (row.asText().contains(cellData))
				return new Row(row.getCells());
		}
		return null;
	}

}
