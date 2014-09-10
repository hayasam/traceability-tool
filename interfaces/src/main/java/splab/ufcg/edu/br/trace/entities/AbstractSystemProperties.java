package splab.ufcg.edu.br.trace.entities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import splab.ufcg.edu.br.trace.exceptions.PropertiesException;

public abstract class AbstractSystemProperties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6724108483002592559L;

	protected static final int KEY_VALUE_LENGHT = 2;

	/**
	 * Default ConectionProperties constructor
	 * 
	 * @param String
	 *            path to properties file
	 * @throws PropertiesException
	 *             if cannot read properties files
	 */
	public AbstractSystemProperties(String path) throws PropertiesException {
		this.readPropertiesFile(path);
	}

	/**
	 * Open file in input path and read declared properties
	 * 
	 * @param String
	 *            path to properties file
	 * @throws PropertiesException
	 *             if cannot read properties files
	 */
	protected void readPropertiesFile(String path) throws PropertiesException {

		if (path == null) {
			throw new PropertiesException(
					"Path to properties file cannot be null");
		}

		FileInputStream fileInput = null;
		BufferedReader reader = null;

		try {

			fileInput = new FileInputStream(path);
			reader = new BufferedReader(new InputStreamReader(fileInput));

			this.readProperties(reader);

		} catch (FileNotFoundException ex) {
			StringBuilder message = new StringBuilder();
			message.append("File not found in path: ");
			message.append(path);

			throw new PropertiesException(message.toString(), ex);
		} catch (Exception ex) {
			throw new PropertiesException(ex.getMessage(), ex);
		} finally {
			try {
				if (fileInput != null) {
					fileInput.close();
				}

				if (reader != null) {
					reader.close();
				}

			} catch (IOException e) {
				throw new PropertiesException(e.getMessage(), e);

			}
		}
	}

	protected void setPropertie(String line) throws PropertiesException {
		
		if(line.startsWith("#") || line.isEmpty()){
			return;
		}
		
		
		String[] splitLine = line.split("=");

		if (splitLine.length != KEY_VALUE_LENGHT) {
			StringBuilder message = new StringBuilder();
			message.append("Cannot read propertie in line: ");
			message.append(line);
			throw new PropertiesException(message.toString());
		}

		String key = splitLine[0].trim();
		String value = splitLine[1].trim();
		

		this.setPropertyByKey(key, value, line);

	}

	protected void readProperties(BufferedReader reader) throws IOException,
			PropertiesException {

		String line = reader.readLine();
		while (line != null) {

			this.setPropertie(line);
			line = reader.readLine();
		}

	}

	public abstract void setPropertyByKey(String key, String value, String line) throws PropertiesException;

}
