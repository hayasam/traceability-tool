package br.edu.ufcg.splab.test.searchable.service;

import java.io.File;
import java.io.InputStream;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import br.edu.ufcg.splab.indexable.service.ApacheSolrJIndexer;
import br.edu.ufcg.splab.searchable.service.ApacheSolrJQuerier;

@RunWith(Arquillian.class)
public class ApacheSolrJQuerierTest {

	@Inject
	private ApacheSolrJIndexer indexer;

	@Inject
	private ApacheSolrJQuerier querier;
	
	private boolean indexed = false;
	
	private TraceLinkList tracelinks;
	
	@Deployment
	public static Archive<?> createTestArchive() {
		File[] files = Maven.resolver().loadPomFromFile("pom.xml")
				.importRuntimeDependencies().resolve().withTransitivity()
				.asFile();

		// Create deploy file
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, "br.edu.ufcg.splab")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("storage.properties", "storage.properties")
				.addAsWebInfResource("storage.properties")
				.addAsResource("tracelinks.xml", "tracelinks.xml")
				.addAsWebInfResource("tracelinks.xml")
				.addAsLibraries(
						Maven.resolver()
								.resolve(
										"org.apache.httpcomponents:httpcore:4.2.2")
								.withTransitivity().asFile())
				.addAsLibraries(
						Maven.resolver()
								.resolve("org.apache.solr:solr-solrj:4.7.2")
								.withTransitivity().asFile())
				.addAsLibraries(files);
		// Show the deploy structure
		// System.out.println(war.toString(true));
		return war;
	}

	/**
	 * Set up
	 */
	public void index() {
		if (!indexed) {
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(TraceLinkList.class);
				InputStream inputStream = Thread.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("tracelinks.xml");
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();
				this.tracelinks = (TraceLinkList) jaxbUnmarshaller
						.unmarshal(inputStream);

				indexer.indexTraceLinks(tracelinks);
				this.indexed = true;
			} catch (JAXBException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Test
	public void testQueryWithExistingRequirement() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("requirement:Req001");

		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());

		for (TraceLink trace : tracelinks.getTraceLinks()) {
			assertTrue(trace.getRequirements().contains("Req001"));
		}
	}

	@Test
	public void testQueryWithTwoExistingRequirement() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("requirement:Req001 AND requirement:Req002");

		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());

		for (TraceLink trace : tracelinks.getTraceLinks()) {
			assertTrue(trace.getRequirements().contains("Req001")
					&& trace.getRequirements().contains("Req002"));
		}
	}

	@Test
	public void testQueryWithNonExistingRequirement() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("requirement:Req005");

		assertNotNull(tracelinks);
		assertTrue(tracelinks.getTraceLinks().isEmpty());
	}

	@Test
	public void testQueryWithMultipleRequirement() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("requirement:Req001 OR requirement:Req002");

		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());

		for (TraceLink trace : tracelinks.getTraceLinks()) {
			assertTrue(trace.getRequirements().contains("Req001")
					|| trace.getRequirements().contains("Req002"));
		}
	}

	@Test
	public void testQueryWithExistingSemantic() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("semantic:Overlaps");

		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());

		for (TraceLink trace : tracelinks.getTraceLinks()) {
			assertEquals("Overlaps", trace.getSemantic());
		}
	}

	@Test
	public void testQueryWithNonExistingSemantic() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("semantic:Parent");

		assertNotNull(tracelinks);
		assertTrue(tracelinks.getTraceLinks().isEmpty());
	}

	@Test
	public void testQueryWithMultipleSemantic() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("semantic:Overlaps OR semantic:Override");

		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());

		for (TraceLink trace : tracelinks.getTraceLinks()) {
			assertTrue("Override".equals(trace.getSemantic())
					|| "Overlaps".equals(trace.getSemantic()));
		}
	}

	@Test
	public void testQueryWithExistingType() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("artifact_type:SourceCode");

		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());

		for (TraceLink trace : tracelinks.getTraceLinks()) {
			assertEquals("SourceCode", trace.getArtifactType());
		}
	}

	@Test
	public void testQueryWithNonExistingType() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("artifact_type:TestCase");

		assertNotNull(tracelinks);
		assertTrue(tracelinks.getTraceLinks().isEmpty());
	}

	@Test
	public void testQueryWithMultipleType() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("artifact_type:SourceCode OR artifact_type:UseCase");

		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());

		for (TraceLink trace : tracelinks.getTraceLinks()) {
			assertTrue("SourceCode".equals(trace.getArtifactType())
					|| "UseCase".equals(trace.getArtifactType()));
		}
	}

	@Test
	public void testQueryWithExistingArtifact() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("artifact:\"Subscribe Person UC\"");

		assertNotNull(tracelinks);
		assertFalse(tracelinks.getTraceLinks().isEmpty());

		for (TraceLink trace : tracelinks.getTraceLinks()) {
			assertEquals("Subscribe Person UC", trace.getArtifact());
		}
	}

	@Test
	public void testQueryWithNonExistingArtifact() {
		this.index();
		TraceLinkList tracelinks = this.querier
				.queryTraceLinks("artifact:\"Subscribe  UC\"");

		assertNotNull(tracelinks);
		assertTrue(tracelinks.getTraceLinks().isEmpty());
	}
}
