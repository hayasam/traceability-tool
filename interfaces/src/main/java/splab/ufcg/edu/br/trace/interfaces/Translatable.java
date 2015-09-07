package splab.ufcg.edu.br.trace.interfaces;

import java.io.File;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;

/**
 * Interface that translators must complies with the set of traceable artifacts should be read.
 * or written into a mapped trace link representation
 * 
 * @author Arthur
 *
 */
public interface Translatable {

	/**
	 * Read trace links from a given file and map them into
	 * TraceLink objects
	 *  
	 * @return TraceLinkList - List<TraceLink> extracted trace links from file
	 */
	TraceLinkList read(File file);

	/**
	 * Write set of trace links into a file 
	 * 
	 * @param file - file containing mapped trace links to the trace link representation
	 * @param tracelinks - list of trace links to be written
	 * 
	 */
	void write(File file, TraceLinkList tracelinks);
}
