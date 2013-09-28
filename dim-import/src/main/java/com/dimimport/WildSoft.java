package com.dimimport;

import static org.apache.commons.io.FileUtils.writeStringToFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.webimport.WebSource;
import com.webimport.webtable.Row;

public class WildSoft extends Import {
	public static void processItem(File file) throws IOException {
		Row row = ws.tables().get(4).rows().get(0);
		// String[] names = row.getData(0).split("\r\n");
		String item = row.getData(0).replace("\r\n", "") + ";";

		List<Row> rows = ws.tables().get(7).rows();
		String year = null;
		String code = null;
		String team = null;

		for (int i = 1; i < rows.size(); i++) {
			Row innerRow = rows.get(i);
			// String[] years = innerRow.getData(2).split("  ");
			String yearTmp = innerRow.getData(0);
			String codeTmp = innerRow.getData(1);
			String teamTmp = innerRow.getData(2);
			if (!yearTmp.isEmpty())
				year = yearTmp;
			if (!codeTmp.isEmpty())
				code = codeTmp;
			if (!teamTmp.isEmpty())
				team = teamTmp;
			writeStringToFile(file, item + year + ";" + code + ";" + team + ";" + innerRow.getData(3) + "\n", true);
		}
	}

	public static void main(String[] args) throws IOException {
		String uri = getProperty("baseuri");
		ws = new WebSource(uri);
		File file = new File(getProperty("outputfile"));

		for (String ref : ws.tables().get(1).rows().get(0).getRefs(0)) {
			ws.goToPage("http://" + ws.currentHost() + "/" + ref);
			for (String item : ws.tables().get(3).rows().get(1).getRefs(0)) {
				ws.goToPage("http://" + ws.currentHost() + "/" + item);
				processItem(file);
			}
		}

	}
}
