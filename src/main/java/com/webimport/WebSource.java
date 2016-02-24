package com.webimport;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.webimport.webtable.Table;

public class WebSource {
	private final WebClient webClient;
	private HtmlPage page;

	public WebSource(String uri) throws IOException {
		this.webClient = new WebClient();
		this.webClient.getOptions().setJavaScriptEnabled(false);
		this.page = webClient.getPage(uri);
	}

	public List<Table> tables() {
		List<Table> result = new ArrayList<Table>();

		for (DomElement table : this.page.getElementsByTagName("table")) {
			result.add(new Table((HtmlTable) table));
		}
		return result;
	}

	public void close() {
		this.webClient.closeAllWindows();
	}

	public Table getTableByTitle(String title) {
		DomElement tableTitle = this.page.getFirstByXPath("//table/tbody/tr[th='" + title + "']");
		if (tableTitle == null)
			return null;
		return new Table((HtmlTable) tableTitle.getParentNode().getParentNode());
	}

	public void goToPage(String uri) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		this.page = webClient.getPage(uri);
	}

	public String currentUri() {
		return this.page.getUrl().toString();
	}

	public String currentHost() {
		return this.page.getUrl().getHost();
	}
}
