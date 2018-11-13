package com.dimimport;

import com.webimport.WebSource;

import java.io.IOException;
import java.util.Properties;

public class Import {

	protected static WebSource ws;
	private static final Properties prop = new Properties();

	protected static String getProperty(String name) throws IOException {
		if (prop.isEmpty())
			prop.load(Import.class.getClassLoader().getResourceAsStream("import.properties"));
		return prop.getProperty(name);
	}
}