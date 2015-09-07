package splab.ufcg.edu.br.trace.interfaces;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;



/**
 * Interface that the search engine must complies with
 * It defines how trace links can be queried according to input parameters
 * 
 * @author Arthur
 *
 */
public interface Searchable {
	
	/**
	 * Query trace links according to a set of parameters
	 * 
	 * @param queryParameter
	 * @return TraceLinkLink - containing all trace links that conforms to query parameters
	 */
	TraceLinkList queryTraceLinks(String query);

}
