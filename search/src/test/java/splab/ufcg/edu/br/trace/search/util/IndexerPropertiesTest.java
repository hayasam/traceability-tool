package splab.ufcg.edu.br.trace.search.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.trace.exceptions.PropertiesException;
import splab.ufcg.edu.br.trace.search.util.ExecutionMode;
import splab.ufcg.edu.br.trace.search.util.IndexerProperties;

@RunWith(JUnit4.class)
public class IndexerPropertiesTest {

	private final String path = "/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/search/src/test/resources/indexer.properties";

	@SuppressWarnings("unused")
	@Test(expected = PropertiesException.class)
	public void testPropertiesNullPath() throws PropertiesException {

		IndexerProperties properties = new IndexerProperties(null);
	}

	@SuppressWarnings("unused")
	@Test(expected = PropertiesException.class)
	public void testPropertiesFileNotFound() throws PropertiesException {

		IndexerProperties properties = new IndexerProperties(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/search/src/test/resources/no-file.properties");
	}

	@SuppressWarnings("unused")
	@Test(expected = PropertiesException.class)
	public void testPropertiesWrongPropertie() throws PropertiesException {

		IndexerProperties properties = new IndexerProperties(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/search/src/test/resources/indexer-error.properties");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = PropertiesException.class)
	public void testPropertiesUnmappedMode() throws PropertiesException {

		IndexerProperties properties = new IndexerProperties(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/search/src/test/resources/indexer-mode-error.properties");
	}	

	@Test
	public void testProperties() throws PropertiesException {

		IndexerProperties properties = new IndexerProperties(path);

		assertEquals(ExecutionMode.FULL, properties.getMode());
		assertEquals(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/search/src/test/resources/import",
				properties.getDirectory());

	}

}
