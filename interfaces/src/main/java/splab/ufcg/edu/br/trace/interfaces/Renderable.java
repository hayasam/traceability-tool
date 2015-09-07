package splab.ufcg.edu.br.trace.interfaces;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.enumeration.TraceLinkElementEnum;

/**
 * Interface that the specify how trace links can be represented 
 * 
 * @author Arthur
 *
 */
public interface Renderable {

	/**
	 * Gets a panel with all containg elements and data structures necessary to represent the trace links
	 * @param Trace link list to build panel
	 * @return
	 */
	javax.swing.JPanel getRepresentation(TraceLinkList tracelinks);
	
	/**
	 * Filter represented trace links according to passed parameters
	 * 
	 * @param TraceLinkElementEnum element to be filtered
	 * @param String filter value
	 * 
	 */
	void filter(TraceLinkElementEnum filterElement, String filterValue);
	

}
