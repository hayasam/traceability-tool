package splab.ufcg.edu.br.trace.indexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.trace.exceptions.IndexException;
import splab.ufcg.edu.br.trace.exceptions.PropertiesException;
import splab.ufcg.edu.br.trace.search.util.ConnectionProperties;
import splab.ufcg.edu.br.trace.search.util.IndexerProperties;

@RunWith(JUnit4.class)
public class ApacheSolrJIndexerTest {

	private ApacheSolrJIndexer indexer;

	private ConnectionProperties connection;

	private IndexerProperties indexerProperties;

	private File directory;

	private Properties properties;
	
	private static final String INDEXER_DIRECTORY = "indexer.directory";

	@Before
	public void setUp() throws Exception {


		this.properties = new Properties();
		InputStream inputStream = getClass().getResourceAsStream(
				"/trace-tool.properties");

		this.properties.load(inputStream);

		this.indexer = new ApacheSolrJIndexer(this.properties);

		this.directory = new File(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/search/src/test/resources/import");

	}

	// @Test(expected = IndexException.class)
	// public void testIndexingWithoutProperties() throws IndexException {
	// this.indexer.indexTraceLinks(directory);
	// }
	//
	// @Test(expected = IndexException.class)
	// public void testIndexingWithoutIndexProperties() throws IndexException {
	// // this.indexer.setConnectionProperties(connection);
	// this.indexer.indexTraceLinks(directory);
	// }

	// @Test(expected = IndexException.class)
	// public void testIndexingWithoutConnectionProperties() throws
	// IndexException {
	// // this.indexer.setIndexerProperties(indexerProperties);
	// this.indexer.indexTraceLinks(directory);
	// }

	@Test
	public void testClearIndexDatabase() throws IndexException {
		this.indexer.clearIndexDatabase();
		assertTrue(this.indexer.isEmpty());
	}

//	@Test(expected = IndexException.class)
//	public void testPartialIndexing() throws PropertiesException,
//			IndexException {
//
//		// this.indexer.setIndexerProperties(indexerProperties);
//		// this.indexer.setConnectionProperties(connection);
//
//		try {
//			this.indexer.indexTraceLinks(directory);
//		} catch (IndexException ex) {
//
//			assertEquals(
//					"Unexpected error while indexing tracelinks: Not yet implemented",
//					ex.getMessage());
//
//			throw ex;
//		}
//
//	}

	@Test
	public void testFullIndexing() throws PropertiesException, IndexException {

		this.indexer.indexTraceLinks(directory);

		assertFalse(this.indexer.isEmpty());

	}

}
