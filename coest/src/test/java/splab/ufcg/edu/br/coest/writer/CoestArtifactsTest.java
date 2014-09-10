package splab.ufcg.edu.br.coest.writer;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.coest.entities.MappedArtifacts;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.exceptions.TraceLinkException;

@RunWith(JUnit4.class)
public class CoestArtifactsTest {
	
	private String[] files;

	private CoestArtifactReader reader;
	
	
	@Before
	public void setUp() throws TraceLinkException {
		files = new String[]{
				"/easyClinic_Classes.xml",
				"/easyClinic_TestCases.xml",
				"/eTour_Classes.xml",
				"/smos_Classes.xml",
				"/wv_cchit_Internal_Requirements.xml"};

		reader = new CoestArtifactReader();
		
	}


	@Test
	public void testEasyClinicClasses() throws Exception {
		
		URL resource = getClass().getResource(files[0]);
		File file = new File(resource.toURI());		
		MappedArtifacts mappedRequiremens = reader.read(file);		
		assertNotNull(mappedRequiremens);
	}
	
	@Test
	public void testEasyClinicTestCases() throws Exception {
		URL resource = getClass().getResource(files[1]);
		File file = new File(resource.toURI());		
		MappedArtifacts mappedRequiremens = reader.read(file);		
		assertNotNull(mappedRequiremens);
	}
	
	@Test
	public void testEtourClasses() throws Exception {
		URL resource = getClass().getResource(files[2]);
		File file = new File(resource.toURI());		
		MappedArtifacts mappedRequiremens = reader.read(file);		
		assertNotNull(mappedRequiremens);
	}
	
	
	@Test
	public void testSmosClasses() throws Exception {
		URL resource = getClass().getResource(files[3]);
		File file = new File(resource.toURI());		
		MappedArtifacts mappedRequiremens = reader.read(file);		
		assertNotNull(mappedRequiremens);
	}
	
	@Test
	public void testWvcchit_Internal_Requirements() throws Exception {
		URL resource = getClass().getResource(files[4]);
		File file = new File(resource.toURI());		
		MappedArtifacts mappedRequiremens = reader.read(file);		
		assertNotNull(mappedRequiremens);
	}
	
	
	

}
