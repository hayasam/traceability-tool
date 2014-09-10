package splab.ufcg.edu.br.coest.writer;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.coest.entities.MappedArtifacts;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.exceptions.TraceLinkException;

@RunWith(JUnit4.class)
public class CoestTraceLinksTest {

	private String[] artifactFiles;

	private CoestArtifactReader artifactReader;

	private String[] tarcelinkFiles;

	private CoestTraceLinksReader linksReader;

	@Before
	public void setUp() throws TraceLinkException {

		artifactFiles = new String[] {
				"/easyClinic_Classes.xml",
				"/easyClinic_TestCases.xml",
				"/eTour_Classes.xml",
				"/smos_Classes.xml",
				"/wv_cchit_Internal_Requirements.xml" };

		tarcelinkFiles = new String[] {
				"/easyClinic_Links_UC_CC.xml",
				"/easyClinic_Links_UC_TC.xml",
				"/eTour_Links_Req_Code.xml",
				"/smos_Links_Req_Code.xml",
				"/wv_cchit_Links_Req_Req.xml" };

		artifactReader = new CoestArtifactReader();

		linksReader = new CoestTraceLinksReader();

	}

	@Test
	public void testEasyClinicTraceLinks() throws Exception {
		
		URL artifactReource = getClass().getResource(artifactFiles[0]);
		URL traceLinkReource = getClass().getResource(tarcelinkFiles[0]);
		
		
		File artifactFile = new File(artifactReource.toURI());
		MappedArtifacts map = artifactReader.read(artifactFile);
		assertNotNull(map);

		File linksFile = new File(traceLinkReource.toURI());
		TraceLinkList traceLinks = linksReader.read(linksFile, map);
		assertNotNull(traceLinks);
		
		artifactReource = getClass().getResource(artifactFiles[1]);
		traceLinkReource = getClass().getResource(tarcelinkFiles[1]);
		

		artifactFile = new File(artifactReource.toURI());
		map = artifactReader.read(artifactFile);
		assertNotNull(map);

		linksFile = new File(traceLinkReource.toURI());
		traceLinks = linksReader.read(linksFile, map);
		assertNotNull(traceLinks);

	}

	@Test
	public void testEtourTraceLinks() throws Exception {
		
		URL artifactReource = getClass().getResource(artifactFiles[2]);
		URL traceLinkReource = getClass().getResource(tarcelinkFiles[2]);
		
		File artifactFile = new File(artifactReource.toURI());
		MappedArtifacts map = artifactReader.read(artifactFile);
		assertNotNull(map);

		File linksFile = new File(traceLinkReource.toURI());
		TraceLinkList traceLinks = linksReader.read(linksFile, map);
		assertNotNull(traceLinks);

	}

	@Test
	public void testSmosTraceLinks() throws Exception {
		
		URL artifactReource = getClass().getResource(artifactFiles[3]);
		URL traceLinkReource = getClass().getResource(tarcelinkFiles[3]);
		
		File artifactFile = new File(artifactReource.toURI());
		MappedArtifacts map = artifactReader.read(artifactFile);
		assertNotNull(map);

		File linksFile = new File(traceLinkReource.toURI());
		TraceLinkList traceLinks = linksReader.read(linksFile, map);
		assertNotNull(traceLinks);

	}
	
	@Test
	public void testWvcchitTraceLinks() throws Exception {
		
		URL artifactReource = getClass().getResource(artifactFiles[4]);
		URL traceLinkReource = getClass().getResource(tarcelinkFiles[4]);
		
		File artifactFile = new File(artifactReource.toURI());
		MappedArtifacts map = artifactReader.read(artifactFile);
		assertNotNull(map);

		File linksFile = new File(traceLinkReource.toURI());
		TraceLinkList traceLinks = linksReader.read(linksFile, map);
		assertNotNull(traceLinks);

	}

}
