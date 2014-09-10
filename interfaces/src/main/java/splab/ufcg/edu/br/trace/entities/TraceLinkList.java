package splab.ufcg.edu.br.trace.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * List that group a set of trace links. It is also the root element of a xml
 * mapping through java xml biding API
 * 
 * @author Arthur
 * 
 * @param <TraceLink>
 */
@XmlRootElement(name = "TraceLinks")
@XmlSeeAlso({ splab.ufcg.edu.br.trace.entities.TraceLink.class })
public class TraceLinkList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5089929726896738828L;

	protected List<TraceLink> traceLinks;

	/**
	 * Default empty constructor. Mandatory because jaxb API
	 */
	public TraceLinkList() {
		this.traceLinks = new ArrayList<TraceLink>();
	}

	/**
	 * Constructor with list parameter.
	 * 
	 * @param list
	 */
	public TraceLinkList(List<TraceLink> list) {
		super();
		this.traceLinks = list;
	}

	@XmlElement(name = "TraceLink")
	public List<TraceLink> getTraceLinks() {
		return traceLinks;
	}

	public void setTraceLinks(List<TraceLink> traceLinks) {
		this.traceLinks = traceLinks;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[");
		if (traceLinks != null) {
			for (int i = 0; i < traceLinks.size(); i++) {
				if (i > 0) {
					result.append(", ");
				}
				result.append(traceLinks.get(i).toString());
			}
		}
		result.append("]");
		return result.toString();
	}

	public boolean contais(TraceLink tracelink) {

		if (this.traceLinks != null) {
			return this.traceLinks.contains(tracelink);
		}
		return false;
	}

	public void add(TraceLink traceLink) {
		this.traceLinks.add(traceLink);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((traceLinks == null) ? 0 : traceLinks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TraceLinkList other = (TraceLinkList) obj;
		if (traceLinks == null) {
			if (other.traceLinks != null)
				return false;
		} else if (!traceLinks.equals(other.traceLinks))
			return false;
		return true;
	}

	public Set<String> getArtifactTypes() {
		Set<String> result = new HashSet<String>();

		for (TraceLink tracelink : traceLinks) {
			result.add(tracelink.getArtifactType());
		}

		return result;
	}

	public Set<String> getSemantics() {
		Set<String> result = new HashSet<String>();

		for (TraceLink tracelink : traceLinks) {
			result.add(tracelink.getSemantic());
		}

		return result;
	}

	public Set<String> getRequirements() {
		Set<String> result = new HashSet<String>();

		for (TraceLink tracelink : traceLinks) {
			
			String requirement = tracelink.getRequirement();
			
			result.add(requirement);
			
		}

		return result;
	}
	
	
	public Set<String> getRequirementList() {
		Set<String> result = new HashSet<String>();

		for (TraceLink tracelink : traceLinks) {
			
			List<String> requirements = tracelink.getRequirements();
			
			result.addAll(new HashSet<String>(requirements));
			
		}

		return result;
	}
	
	public Set<String> getArtifacts() {
		Set<String> result = new HashSet<String>();
		
		for (TraceLink tracelink : traceLinks) {
			result.add(tracelink.getArtifact());
		}
		
		return result;
	}

	public List<TraceLink> filterByRequirementSemanticAndType(
			String requirementNode, String semanticNode, String artifactTypeNode) {

		List<TraceLink> relatedTraces = new ArrayList<TraceLink>();
		for (TraceLink tracelink : traceLinks) {

			if (tracelink.getRequirements().contains(requirementNode)
					&& semanticNode.equals(tracelink.getSemantic())
					&& artifactTypeNode.equals(tracelink.getArtifactType())) {
				relatedTraces.add(tracelink);

			}

		}

		return relatedTraces;
	}
	


	public List<TraceLink> filterByType( String artifactTypeNode) {
		
		List<TraceLink> relatedTraces = new ArrayList<TraceLink>();
		for (TraceLink tracelink : traceLinks) {
			
			if (artifactTypeNode.equals(tracelink.getArtifactType())) {
				relatedTraces.add(tracelink);
				
			}
			
		}
		
		return relatedTraces;
	}
	
	public int size() {
		return this.traceLinks.size();
	}

	public boolean isEmpty() {
		return this.traceLinks.isEmpty();
	}

	public boolean contains(Object o) {
		return this.traceLinks.contains(o);
	}

	public Iterator<TraceLink> iterator() {
		return this.traceLinks.iterator();
	}

	public Object[] toArray() {
		return this.traceLinks.toArray();
	}

	public Object[] toArray(Object[] a) {
		return null;
	}

	public boolean add(Object e) {
		if (e instanceof TraceLink) {
			TraceLink tracelink = (TraceLink) e;
			this.traceLinks.add(tracelink);
		}

		return false;
	}

	public boolean containsAll(Collection c) {

		return this.traceLinks.containsAll(c);
	}

	public boolean addAll(Collection<TraceLink> c) {

		return this.traceLinks.addAll(c);
	}

	public boolean addAll(int index, Collection<TraceLink> c) {

		return this.traceLinks.addAll(index, c);
	}

	public boolean removeAll(Collection c) {

		return this.traceLinks.removeAll(c);
	}

	public void clear() {
		this.traceLinks.clear();

	}

	public Object get(int index) {

		return this.traceLinks.get(index);
	}

	public void add(int index, Object element) {
		if (element instanceof TraceLink) {

			this.traceLinks.add(index, (TraceLink) element);
		}

	}

	public Object remove(int index) {

		return this.traceLinks.remove(index);
	}

	public int indexOf(Object o) {

		return this.traceLinks.indexOf(o);
	}

	public ListIterator<TraceLink> listIterator() {

		return this.traceLinks.listIterator();
	}

	public void addAll(TraceLinkList traceLinksTemp) {
		if (traceLinksTemp != null && traceLinksTemp.getTraceLinks() != null){
			this.traceLinks.addAll(traceLinksTemp.getTraceLinks());			
		}
	}

	
}