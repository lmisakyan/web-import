package com.dimimport;

import java.io.IOException;
import java.util.Properties;

import com.webimport.WebSource;

public class Import {

	protected static WebSource ws;
	private static final Properties prop = new Properties();

	protected static String getProperty(String name) throws IOException {
		if (prop.isEmpty())
			prop.load(Import.class.getClassLoader().getResourceAsStream("import.properties"));
		return prop.getProperty(name);
	}

}