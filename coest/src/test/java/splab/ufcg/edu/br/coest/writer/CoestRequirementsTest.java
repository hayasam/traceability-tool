package splab.ufcg.edu.br.coest.writer;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.coest.entities.MappedArtifacts;
import splab.ufcg.edu.br.trace.exceptions.TraceLinkException;

@RunWith(JUnit4.class)
public class CoestRequirementsTest {

	
	private String[] files;

	private CoestArtifactReader reader;
	
	
	@Before
	public void setUp() throws TraceLinkException {
		files = new String[]{
				"/easyClinic_Requirements.xml",
				"/eTour_Requirements.xml",
				"/smos_Requirements.xml",
				"/wv_cchit_Requirements.xml"};

		reader = new CoestArtifactReader();
		
	}

	
	@Test
	public void testEasyClinicRequirements() throws Exception{	
		URL resource = getClass().getResource(files[0]);
		File file = new File(resource.toURI());		
		MappedArtifacts mappedRequiremens = reader.read(file);		
		assertNotNull(mappedRequiremens);		
	}
	
	
	@Test
	public void testEtourRequirements() throws Exception{		
		URL resource = getClass().getResource(files[1]);
		File file = new File(resource.toURI());				
		MappedArtifacts mappedRequiremens = reader.read(file);		
		assertNotNull(mappedRequiremens);		
	}
	
	@Test
	public void testSmosRequirements() throws Exception{		
		URL resource = getClass().getResource(files[2]);
		File file = new File(resource.toURI());				
		MappedArtifacts mappedRequiremens = reader.read(file);		
		assertNotNull(mappedRequiremens);		
	}
	
	@Test
	public void testWvcchitRequirements() throws Exception{		
		URL resource = getClass().getResource(files[3]);
		File file = new File(resource.toURI());				
		MappedArtifacts mappedRequiremens = reader.read(file);		
		assertNotNull(mappedRequiremens);		
	}
	
}
