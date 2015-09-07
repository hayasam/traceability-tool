package br.edu.ufcg.splab.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StorageSystemProperties {

	private final String MODE_PROPERTIE = "indexer.mode";

	private final String DIRECTORY_PROPERTIE = "indexer.directory";

	private final String URL_PROPERTIE = "apache.url";

	private final String CORE_PROPERTIE = "apache.core";

	private ExecutionMode mode;

	private Properties properties;

	public ExecutionMode getMode() {
		return mode;
	}

	public void setMode(ExecutionMode mode) {
		this.mode = mode;
	}

	protected void checkProperties() {
		if (this.properties == null) {
			throw new RuntimeException("Properties file not set.");
		}
	}

	public void loadProperties() {
		try {
			InputStream in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("storage.properties");
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ExecutionMode getIndexMode()  {
		this.checkProperties();
		String mode = properties.getProperty(MODE_PROPERTIE);
		this.mode = ExecutionMode.getEnum(mode);
		return this.mode;
	}

	public String getIndexDirectory()  {
		this.checkProperties();
		return properties.getProperty(DIRECTORY_PROPERTIE);
	}

	public String getConnectionUrl()  {
		this.checkProperties();
		StringBuilder urlConn = new StringBuilder();
		urlConn.append(properties.get(URL_PROPERTIE));
		urlConn.append(properties.get(CORE_PROPERTIE));
		return urlConn.toString();
	}
}
