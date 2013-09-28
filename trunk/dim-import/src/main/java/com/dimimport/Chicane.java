package com.dimimport;

import static org.apache.commons.io.FileUtils.writeStringToFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.webimport.WebSource;
import com.webimport.webtable.Row;
import com.webimport.webtable.Table;

public class Chicane extends Import {
	private static void ptocessTable(Table table) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		if (table == null)
			return;
		File file = new File(getProperty("outputfile"));

		for (Row row : table.rows()) {
			String ref = row.getFirstRef(1);
			if (ref != null) {
				String item = row.getData(1);
				ws.goToPage("http://" + ws.currentHost() + "/" + ref);
				Table et = ws.getTableByTitle(getProperty("innertabletitle"));
				if (et == null) {
					writeStringToFile(file, item + ";\n", "UTF-8", true);
					continue;
				}

				String rowHead = getProperty("innertablerowtitle");
				if (rowHead != null)
					writeStringToFile(file, item + ";" + et.getRowByCellData(rowHead).getData(1) + "\n", "UTF-8", true);

				else {
					// String endHead = et.rowExists("Chassis Designer") ?
					// "Chassis Designer" : "Car Model";
					for (Row innerRow : et.rowsBetweenHeaders(getProperty("innertableupperbound"), getProperty("innertablelowerbound"))) {
						String[] years = innerRow.getData(2).split(", ");

						for (String year : years) {
							String[] range = year.split(" - ");
							writeStringToFile(file, item + ";" + innerRow.getData(0) + ";" + range[0] + ";" + range[range.length - 1]
									+ "\n", "UTF-8", true);
						}
					}
				}
			}
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String uri = getProperty("baseuri");
		ws = new WebSource(uri);
		String title = getProperty("basetabletitle");
		ptocessTable(ws.getTableByTitle(title));

		ws.goToPage(uri);
		for (String ref : ws.tables().get(5).rows().get(0).getRefs(0)) {
			ws.goToPage("http://" + ws.currentHost() + "/" + ref);
			ptocessTable(ws.getTableByTitle(title));
		}
	}

}
