package splab.ufcg.edu.br.trace.querier;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.trace.entities.ApacheSolrJFieldEnum;
import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.exceptions.QueryException;
import splab.ufcg.edu.br.trace.indexer.ApacheSolrJIndexer;
import splab.ufcg.edu.br.trace.search.util.ConnectionProperties;
import splab.ufcg.edu.br.trace.search.util.IndexerProperties;

@RunWith(JUnit4.class)
public class ApacheSolrJQuerierTest {
	
	private ApacheSolrJIndexer indexer;
	
	private ApacheSolrJQuerier querier;

	private ConnectionProperties connection;

	private IndexerProperties indexerProperties;

	private Properties properties;

	private File directory;
	
	


	
	@Before
	public void setUp() throws Exception {

		this.properties = new Properties();
		InputStream inputStream = getClass().getResourceAsStream(
				"/trace-tool.properties");

		this.properties.load(inputStream);

		this.indexer = new ApacheSolrJIndexer(this.properties);

		this.directory = new File(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/search/src/test/resources/import");
		
		this.indexer.clearIndexDatabase();
		this.indexer.indexTraceLinks(directory);
		
		this.querier = new ApacheSolrJQuerier(properties);


	}
	
//	@Test
//	public void testQueryWithoutParameters() throws QueryException{
//		TraceLinkList tracelinks = this.querier.queryTraceLinks(null);
//		
//		assertNotNull(tracelinks);
//	}
	
	
	@Test
	public void testQueryWithExistingRequirement() throws QueryException{
		

		
		TraceLinkList tracelinks = this.querier.queryTraceLinks("requirement:Req001");
		
		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());
		
		for(TraceLink trace: tracelinks.getTraceLinks()) {
			assertTrue(trace.getRequirements().contains("Req001"));
		}
	}
	
	@Test
	public void testQueryWithTwoExistingRequirement() throws QueryException{
		

		
		TraceLinkList tracelinks = this.querier.queryTraceLinks("requirement:Req001 AND requirement:Req002");
		
		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());
		
		for(TraceLink trace: tracelinks.getTraceLinks()) {
			assertTrue(trace.getRequirements().contains("Req001") && trace.getRequirements().contains("Req002"));
		}
	}
	
	
	@Test
	public void testQueryWithNonExistingRequirement() throws QueryException{
		

		
		TraceLinkList tracelinks = this.querier.queryTraceLinks("requirement:Req005");
		
		assertNotNull(tracelinks);
		assertTrue(tracelinks.getTraceLinks().isEmpty());
	}
	
	
	@Test
	public void testQueryWithMultipleRequirement() throws QueryException{
		
		
		TraceLinkList tracelinks = this.querier.queryTraceLinks("requirement:Req001 OR requirement:Req002");
		
		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());
		
		for(TraceLink trace: tracelinks.getTraceLinks()) {
//			assertTrue("Req001".equals(trace.getRequirement()) || "Req002".equals(trace.getRequirement()));
			assertTrue(trace.getRequirements().contains("Req001") || trace.getRequirements().contains("Req002"));
		}
	}
	
	
	
	@Test
	public void testQueryWithExistingSemantic() throws QueryException{
		

		
		TraceLinkList tracelinks = this.querier.queryTraceLinks("semantic:Overlaps");
		
		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());
		
		for(TraceLink trace: tracelinks.getTraceLinks()) {
			assertEquals("Overlaps", trace.getSemantic());
		}
	}
	
	
	@Test
	public void testQueryWithNonExistingSemantic() throws QueryException{
		

		TraceLinkList tracelinks = this.querier.queryTraceLinks("semantic:Parent");
		
		assertNotNull(tracelinks);
		assertTrue(tracelinks.getTraceLinks().isEmpty());
	}
	
	
	@Test
	public void testQueryWithMultipleSemantic() throws QueryException{
		

		TraceLinkList tracelinks = this.querier.queryTraceLinks("semantic:Overlaps OR semantic:Override");
		
		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());
		
		for(TraceLink trace: tracelinks.getTraceLinks()) {
			assertTrue("Override".equals(trace.getSemantic()) || "Overlaps".equals(trace.getSemantic()));
		}
	}
	
	
	@Test
	public void testQueryWithExistingType() throws QueryException{
		

		TraceLinkList tracelinks = this.querier.queryTraceLinks("artifact_type:SourceCode");
		
		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());
		
		for(TraceLink trace: tracelinks.getTraceLinks()) {
			assertEquals("SourceCode", trace.getArtifactType());
		}
	}
	
	
	@Test
	public void testQueryWithNonExistingType() throws QueryException{
		

		TraceLinkList tracelinks = this.querier.queryTraceLinks("artifact_type:TestCase");
		
		assertNotNull(tracelinks);
		assertTrue(tracelinks.getTraceLinks().isEmpty());
	}
	
	
	@Test
	public void testQueryWithMultipleType() throws QueryException{
		

		TraceLinkList tracelinks = this.querier.queryTraceLinks("artifact_type:SourceCode OR artifact_type:UseCase");
		
		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());
		
		for(TraceLink trace: tracelinks.getTraceLinks()) {
			assertTrue("SourceCode".equals(trace.getArtifactType()) || "UseCase".equals(trace.getArtifactType()));
		}
	}
	
	@Test
	public void testQueryWithExistingArtifact() throws QueryException{
		
		

		TraceLinkList tracelinks = this.querier.queryTraceLinks("artifact:\"Subscribe Person UC\"");
		
		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());
		
		for(TraceLink trace: tracelinks.getTraceLinks()) {
			assertEquals("Subscribe Person UC", trace.getArtifact());
		}
	}
	
	
	@Test
	public void testQueryWithNonExistingArtifact() throws QueryException{
		

		TraceLinkList tracelinks = this.querier.queryTraceLinks("artifact:\"Subscribe  UC\"");
		
		assertNotNull(tracelinks);
		assertTrue(tracelinks.getTraceLinks().isEmpty());
	}
	
}
