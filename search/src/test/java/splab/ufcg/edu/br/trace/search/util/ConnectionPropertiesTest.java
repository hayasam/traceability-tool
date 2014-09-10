package splab.ufcg.edu.br.trace.search.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.trace.exceptions.PropertiesException;
import splab.ufcg.edu.br.trace.search.util.ConnectionProperties;

@RunWith(JUnit4.class)
public class ConnectionPropertiesTest {

	private final String path = "/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/search/src/test/resources/apache-solr.properties";

	@SuppressWarnings("unused")
	@Test(expected = PropertiesException.class)
	public void testPropertiesNullPath() throws PropertiesException {

		ConnectionProperties properties = new ConnectionProperties(null);
	}

	@SuppressWarnings("unused")
	@Test(expected = PropertiesException.class)
	public void testPropertiesFileNotFound() throws PropertiesException {

		ConnectionProperties properties = new ConnectionProperties(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/search/src/test/resources/no-file.properties");
	}

	@SuppressWarnings("unused")
	@Test(expected = PropertiesException.class)
	public void testPropertiesWrongPropertie() throws PropertiesException {

		ConnectionProperties properties = new ConnectionProperties(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/search/src/test/resources/apache-solr-error.properties");
	}

	@Test
	public void testProperties() throws PropertiesException {

		ConnectionProperties properties = new ConnectionProperties(path);

		assertEquals("http://localhost:8983/solr/", properties.getUrl());
		assertEquals("trace_collection", properties.getCore());
		
	}

}
