package com.webimport;

import org.junit.After;

import java.io.IOException;

public class WebImportBehaviour {

	protected final WebSource webSource;

	public WebImportBehaviour(String uri) throws IOException {
		webSource = new WebSource(uri);
	}

	@After
	public void closeWebSource() {
		webSource.close();
	}

}