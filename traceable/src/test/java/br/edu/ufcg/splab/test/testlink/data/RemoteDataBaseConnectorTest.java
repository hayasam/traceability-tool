package br.edu.ufcg.splab.test.testlink.data;

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

import br.edu.ufcg.splab.testlink.data.RemoteDataBaseConnector;

@RunWith(Arquillian.class)
public class RemoteDataBaseConnectorTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve().withTransitivity().asFile();

        // Create deploy file    
        WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, "br.edu.ufcg.splab.testlink.data")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("testlink.properties", "testlink.properties")
				.addAsWebInfResource("testlink.properties")
				.addAsLibraries(Maven.resolver().resolve("org.apache.httpcomponents:httpcore:4.2.2").withTransitivity().asFile())
                .addAsLibraries(files);

        // Show the deploy structure
        // System.out.println(war.toString(true)); 
        return war;
	}
	
	@Inject
	private RemoteDataBaseConnector rdbc;
	
	@Test
	public void testLoadProperties() {
		assertNotNull(rdbc.getProperties());
		assertNotNull(rdbc.getTestlinkUsername());
		assertNotNull(rdbc.getTestPassword());
		assertNotNull(rdbc.getTestlinkDbname());
		assertNotNull(rdbc.getTestlinkUrl());
	}
	
	@Test
	public void testGetConnection() {
		assertNotNull(rdbc.getConnection());
	}

}
