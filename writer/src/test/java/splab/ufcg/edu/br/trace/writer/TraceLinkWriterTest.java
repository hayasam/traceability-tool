package splab.ufcg.edu.br.trace.writer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.exceptions.TraceLinkException;

@RunWith(JUnit4.class)
public class TraceLinkWriterTest {

	private File file;

	private TraceLinkWriter writer;

	private TraceLink tracelink;

	private TraceLinkList tracelinklist;

	@Before
	public void setUp() throws TraceLinkException {
		file = new File(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/writer/src/test/resources/tracelinks.xml");

		writer = new TraceLinkWriter();

		tracelink = new TraceLink("Req001", "Overlaps", "Source Code",
				"Java Class");

		tracelinklist = new TraceLinkList();
		tracelinklist.add(new TraceLink("Req001", "Overlaps", "Source Code",
				"Java Class"));
		tracelinklist.add(new TraceLink("Req122", "Overlaps", "Source Code",
				"Java Class"));
		tracelinklist.add(new TraceLink("Req032", "Overlaps", "Source Code",
				"Java Class"));

	}

	@Test
	public void testRead() throws Exception {

		TraceLinkList readTraceLinks = writer.read(file);

		assertNotNull(readTraceLinks);

		assertTrue(readTraceLinks.contais(tracelink));

		assertEquals(tracelinklist, readTraceLinks);

	}

	@Test(expected = TraceLinkException.class)
	public void testReadMalformedTraceLink() throws Exception {

		file = new File(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/writer/src/test/resources/tracelinks-error.xml");

		writer.read(file);

	}

	@Test(expected = Exception.class)
	public void testReadMalformedXML() throws Exception {

		file = new File(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/writer/src/test/resources/malformed-xml.xml");

		writer.read(file);

	}
	
	@Test(expected = Exception.class)
	public void testReadFileNotFound() throws Exception {

		file = new File(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/writer/src/test/resources/not-found.xml");

		writer.read(file);

	}
	
	
	@Test
	public void testWriteAndRead() throws Exception{
		
		file = new File(
				"/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/writer/src/test/resources/write.xml");
		
		writer.write(file, tracelinklist);
		
		TraceLinkList readTraceLinks = writer.read(file);

		assertNotNull(readTraceLinks);
		assertEquals(tracelinklist, readTraceLinks);
		
		
	}
}
