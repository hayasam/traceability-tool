package splab.ufcg.edu.br.trace.interfaces;

import java.io.File;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;


/**
 * Interface that the search engine must complies with
 * It dictates that each translated artifacts should be grouped as
 * a set of trace links 
 * 
 * @author Arthur
 *
 */
public interface Indexable {

	/**
	 * Index trace links into storage data service
	 * 
	 * @throws IndexException
	 */
	void indexTraceLinks(File directory);
	
	/**
	 * Clear all indexed trace links into storage data service
	 * 
	 * @throws IndexException
	 */
	void clearIndexDatabase();

	void indexTraceLinks(TraceLinkList tracelinkList);

}
