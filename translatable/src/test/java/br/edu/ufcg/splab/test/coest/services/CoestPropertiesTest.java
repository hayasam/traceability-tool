package br.edu.ufcg.splab.test.coest.services;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.edu.ufcg.splab.util.CoestProperties;

@RunWith(Arquillian.class)
public class CoestPropertiesTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve().withTransitivity().asFile();

        // Create deploy file    
        WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, "br.edu.ufcg.splab.util")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("coest.properties", "coest.properties")
				.addAsWebInfResource("coest.properties")
				.addAsLibraries(Maven.resolver().resolve("org.apache.httpcomponents:httpcore:4.2.2").withTransitivity().asFile())
                .addAsLibraries(files);

        // Show the deploy structure
        // System.out.println(war.toString(true)); 
        return war;
	}
	
	@Inject
	private CoestProperties properties;
	
	@Test
	public void testLoadProperties() throws Exception {
		properties.loadProperties();
		assertNotNull(properties.getProperties());
		assertNotNull(properties.getRequirementsFile());
		assertNotNull(properties.getArtifactFiles());
		assertNotNull(properties.getArtifactsFile());
		assertNotNull(properties.getTracesFile());
		assertNotNull(properties.getTraceFiles());
	}
}
