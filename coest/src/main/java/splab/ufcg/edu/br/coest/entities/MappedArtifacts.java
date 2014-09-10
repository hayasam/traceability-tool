package splab.ufcg.edu.br.coest.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.entities.TraceQuery;

public class MappedArtifacts implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3616511593363730931L;
	
	private String type;
	
	private Map<String, String> mapIdArtifact;
	
	private TraceLinkList traceLinksList;
	
	public MappedArtifacts(){
		mapIdArtifact = new HashMap<String, String>();
		traceLinksList = new TraceLinkList();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getMapIdArtifact() {
		return mapIdArtifact;
	}

	public void setMapIdArtifact(Map<String, String> mapIdArtifact) {
		this.mapIdArtifact = mapIdArtifact;
	}

	public TraceLinkList getTraceLinksList() {
		
		return traceLinksList;
	}

	public void setTraceLinksList(TraceLinkList traceLinksList) {
		this.traceLinksList = traceLinksList;
	}

	
	
}
