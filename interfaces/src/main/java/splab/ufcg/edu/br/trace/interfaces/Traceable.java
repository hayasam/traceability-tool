package splab.ufcg.edu.br.trace.interfaces;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;

/**
 * Interface that each translator must complies with
 * It dictates that each translated artifacts should be grouped as
 * a set of trace links 
 * 
 * @author Arthur
 *
 */
public interface Traceable {
	
	/**
	 * Get the set of traceable artifacts extracted from a given translator
	 * 
	 * @return Set<TraceLink> set of traceable artifacts
	 */
	TraceLinkList getTraceLinks();
	
	

}
