package br.edu.ufcg.splab.testlink.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class RemoteDataBaseConnector implements Serializable {

	private static final long serialVersionUID = -4938862491702343825L;

	private Properties properties;

	private final String USERNAME_PROPERTIE = "testlink.db.user";

	private final String PASSWORD_PROPERTIE = "testlink.db.passwd";

	private final String DB_NAME_PROPERTIE = "testlink.db.name";

	private final String URL_PROPERTIE = "testlink.url.connection";

	/**
	 * Default constructor
	 */
	public RemoteDataBaseConnector() {
	}

	/**
	 * Connect to testlink database
	 * 
	 * @return SQL Connection
	 * @throws ConnectionException
	 *             if connection cannot be established
	 */
	public Connection getConnection() {
		Connection connection = null;
		try {
			StringBuilder jdbcURL = new StringBuilder();
			jdbcURL.append("jdbc:mysql://");
			jdbcURL.append(this.getTestlinkUrl());
			jdbcURL.append("/");
			jdbcURL.append(this.getTestlinkDbname());

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(jdbcURL.toString(),
					this.getTestlinkUsername(), this.getTestPassword());
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
		return connection;
	}

	public String getTestPassword() {
		this.checkProperties();
		return properties.getProperty(PASSWORD_PROPERTIE);
	}

	public String getTestlinkUsername() {
		this.checkProperties();
		return properties.getProperty(USERNAME_PROPERTIE);
	}

	public String getTestlinkDbname() {
		this.checkProperties();
		return properties.getProperty(DB_NAME_PROPERTIE);
	}

	public String getTestlinkUrl() {
		this.checkProperties();
		return properties.getProperty(URL_PROPERTIE);
	}

	private void checkProperties() {
		if (this.getProperties() == null) {
			throw new RuntimeException("Properties file not set");
		}
	}

	public Properties getProperties() {
		if (this.properties == null) {
			this.loadProperties();
		}
		return properties;
	}

	private void loadProperties() {
		try {
			InputStream in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("testlink.properties");
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
