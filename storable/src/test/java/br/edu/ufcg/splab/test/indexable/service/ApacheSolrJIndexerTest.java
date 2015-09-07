package br.edu.ufcg.splab.test.indexable.service;

import java.io.File;
import java.io.InputStream;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import br.edu.ufcg.splab.indexable.service.ApacheSolrJIndexer;

@RunWith(Arquillian.class)
public class ApacheSolrJIndexerTest {

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

	@Inject
	private ApacheSolrJIndexer indexer;

	@Test
	public void testIndexTraceLinks() throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(TraceLinkList.class);
		InputStream inputStream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream("tracelinks.xml");
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		TraceLinkList tracelinks = (TraceLinkList) jaxbUnmarshaller
				.unmarshal(inputStream);
		
		indexer.indexTraceLinks(tracelinks);
	}
}
