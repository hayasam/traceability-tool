package splab.ufcg.edu.br.trace.interfaces;

import java.io.File;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.exceptions.IndexException;


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
	void indexTraceLinks(File directory) throws IndexException;
	
	/**
	 * Clear all indexed trace links into storage data service
	 * 
	 * @throws IndexException
	 */
	void clearIndexDatabase() throws IndexException;

	void indexTraceLinks(TraceLinkList tracelinkList) throws IndexException;

}
