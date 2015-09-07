package br.edu.ufcg.splab.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CoestProperties {

	private static final String SEMICOLUMN = ";";

	private static final String REQUIREMENTS_FILE = "coest.requirements";

	private static final String ARTIFACT_FILE = "coest.artifacts";

	private static final String TRACE_FILE = "coest.traces";

	protected Properties properties;

	protected static final String WRITER_DIRECTORY = "writer.directory";

	protected void checkProperties() {
		if (this.properties == null) {
			throw new RuntimeException("Properties file not set.");
		}
	}

	public void loadProperties() {
		try {
			InputStream in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("coest.properties");
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] getTraceFiles() {
		this.checkProperties();
		String files = properties.getProperty(TRACE_FILE);
		String[] split = files.split(SEMICOLUMN);

		return split;
	}

	public String[] getArtifactFiles() {
		this.checkProperties();
		String files = properties.getProperty(ARTIFACT_FILE);
		String[] split = files.split(SEMICOLUMN);

		return split;
	}

	public int getArraySize() {
		this.checkProperties();
		String files = properties.getProperty(ARTIFACT_FILE);
		String[] split = files.split(SEMICOLUMN);

		return split.length;
	}

	public boolean isArray() {
		this.checkProperties();
		String files = properties.getProperty(ARTIFACT_FILE);
		return files.contains(SEMICOLUMN);
	}

	public String getArtifactsFile() throws Exception {

		this.checkProperties();
		return properties.getProperty(ARTIFACT_FILE);
	}

	public String getTracesFile() {
		this.checkProperties();

		return properties.getProperty(TRACE_FILE);
	}

	public String getRequirementsFile() {
		this.checkProperties();

		return properties.getProperty(REQUIREMENTS_FILE);
	}

	public Properties getProperties() {
		if (this.properties == null) {
			this.loadProperties();
		}
		return properties;
	}

	public String getWriterDirectory() {
		this.checkProperties();
		return properties.getProperty(WRITER_DIRECTORY);
	}
}
