package br.edu.ufcg.splab.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestLinkProperties {

	protected Properties properties;

	protected static final String WRITER_DIRECTORY = "writer.directory";
	
	protected static final String READER_DIRECTORY = "reader.directory";

	protected void checkProperties() {
		if (this.properties == null) {
			throw new RuntimeException("Properties file not set.");
		}
	}

	public void loadProperties() {
		try {
			InputStream in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("testlink.properties");
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getWriterDirectory() {
		this.checkProperties();
		return properties.getProperty(WRITER_DIRECTORY);
	}
	
	public String getReaderDirectory() {
		this.checkProperties();
		return properties.getProperty(READER_DIRECTORY);
	}
}
