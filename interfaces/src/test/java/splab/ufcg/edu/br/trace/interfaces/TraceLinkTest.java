package splab.ufcg.edu.br.trace.interfaces;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.exceptions.TraceLinkException;



@RunWith(JUnit4.class)
public class TraceLinkTest {
	
	@Test
	public void testTraceLink() throws TraceLinkException{
		TraceLink tracelink = new TraceLink("Req001", "Overlaps", "Source Code", "Java Class");
		
		assertEquals("Req001", tracelink.getRequirement());
		assertEquals("Overlaps", tracelink.getSemantic());
		assertEquals("Source Code", tracelink.getArtifactType());
		assertEquals("Java Class", tracelink.getArtifact());
		
	}
	
//	@SuppressWarnings("unused")
//	@Test(expected = TraceLinkException.class)
//	public void testNullRequirement() throws TraceLinkException{
//		TraceLink tracelink = new TraceLink(null, "Overlaps", "Source Code", "Java Class");
//	}

	
	@SuppressWarnings("unused")
	@Test(expected = TraceLinkException.class)
	public void testNullSemantic() throws TraceLinkException{
		TraceLink tracelink = new TraceLink("Req001", null, "Source Code", "Java Class");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = TraceLinkException.class)
	public void testNullArtifactType() throws TraceLinkException{
		TraceLink tracelink = new TraceLink("Req001", "Overlaps", null, "Java Class");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = TraceLinkException.class)
	public void testNullArtifact() throws TraceLinkException{
		TraceLink tracelink = new TraceLink("Req001", "Overlaps", "Source Code", null);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = TraceLinkException.class)
	public void testEmptyRequirement() throws TraceLinkException{
		TraceLink tracelink = new TraceLink("", "Overlaps", "Source Code", "Java Class");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = TraceLinkException.class)
	public void testEmptySemantic() throws TraceLinkException{
		TraceLink tracelink = new TraceLink("Req001", "", "Source Code", "Java Class");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = TraceLinkException.class)
	public void testEmptyArtifactType() throws TraceLinkException{
		TraceLink tracelink = new TraceLink("Req001", "Overlaps", "", "Java Class");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = TraceLinkException.class)
	public void testEmptyArtifact() throws TraceLinkException{
		TraceLink tracelink = new TraceLink("Req001", "Overlaps", "Source Code", "");
	}

}
