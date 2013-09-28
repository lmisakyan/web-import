package com.webimport;

import java.io.IOException;

import org.junit.After;

import com.webimport.WebSource;

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