package splab.ufcg.edu.br.trace.interfaces;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.trace.entities.TraceLink;



@RunWith(JUnit4.class)
public class TraceLinkTest {
	
	@Test
	public void testTraceLink() {
		TraceLink tracelink = new TraceLink("Req001", "Overlaps", "Source Code", "Java Class");
		
		assertEquals("Req001", tracelink.getRequirement());
		assertEquals("Overlaps", tracelink.getSemantic());
		assertEquals("Source Code", tracelink.getArtifactType());
		assertEquals("Java Class", tracelink.getArtifact());
		
	}
	
//	@SuppressWarnings("unused")
//	@Test(expected = RuntimeException.class)
//	public void testNullRequirement() {
//		TraceLink tracelink = new TraceLink(null, "Overlaps", "Source Code", "Java Class");
//	}

	
	@SuppressWarnings("unused")
	@Test(expected = RuntimeException.class)
	public void testNullSemantic() {
		TraceLink tracelink = new TraceLink("Req001", null, "Source Code", "Java Class");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = RuntimeException.class)
	public void testNullArtifactType() {
		TraceLink tracelink = new TraceLink("Req001", "Overlaps", null, "Java Class");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = RuntimeException.class)
	public void testNullArtifact() {
		TraceLink tracelink = new TraceLink("Req001", "Overlaps", "Source Code", null);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = RuntimeException.class)
	public void testEmptyRequirement() {
		TraceLink tracelink = new TraceLink("", "Overlaps", "Source Code", "Java Class");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = RuntimeException.class)
	public void testEmptySemantic() {
		TraceLink tracelink = new TraceLink("Req001", "", "Source Code", "Java Class");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = RuntimeException.class)
	public void testEmptyArtifactType() {
		TraceLink tracelink = new TraceLink("Req001", "Overlaps", "", "Java Class");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = RuntimeException.class)
	public void testEmptyArtifact() {
		TraceLink tracelink = new TraceLink("Req001", "Overlaps", "Source Code", "");
	}

}
