package splab.ufcg.edu.br.trace.testlink.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import splab.ufcg.edu.br.trace.exceptions.ConnectionException;
import splab.ufcg.edu.br.trace.exceptions.TraceLinkException;



public class Connector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4938862491702343825L;
	private Properties properties;


	private final String USERNAME_PROPERTIE = "testlink.db.user";
	private final String PASSWORD_PROPERTIE = "testlink.db.passwd";
	private final String DB_NAME_PROPERTIE = "testlink.db.name";
	private final String URL_PROPERTIE = "testlink.url.connection";


	public Connector() {

	}




	/**
	 * Connect to testlink database
	 * 
	 * @return SQL Connection
	 * @throws ConnectionException if connection cannot be established
	 */
	public Connection getConnection() throws ConnectionException {

		
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
			throw new ConnectionException(ex.getMessage(), ex);
		} 

		return connection;
	}




	private String getTestPassword() throws TraceLinkException {
		this.checkProperties();
		return properties.getProperty(PASSWORD_PROPERTIE);
	}




	private String getTestlinkUsername() throws TraceLinkException {
		this.checkProperties();
		return properties.getProperty(USERNAME_PROPERTIE);
	}




	private String getTestlinkDbname() throws TraceLinkException {
		this.checkProperties();
		return properties.getProperty(DB_NAME_PROPERTIE);
	}




	private String getTestlinkUrl() throws TraceLinkException {
		this.checkProperties();
		return properties.getProperty(URL_PROPERTIE);
	}




	private void checkProperties() throws TraceLinkException {
		if(this.getProperties() == null) {
			throw new TraceLinkException("Properties file not set");
		}
		
	}




	public Properties getProperties() {
		return properties;
	}




	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
