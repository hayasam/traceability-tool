package splab.ufcg.edu.br.trace.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappedTracelink implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3810409146673475356L;
	private Map<InnerTracelink, List<String>> mapArtifactRequirements;
	
	
	public MappedTracelink() {
		mapArtifactRequirements = new HashMap<InnerTracelink, List<String>>();
	}
	
	public void put(InnerTracelink innerTracelink, String requirement) {
		if (!mapArtifactRequirements.containsKey(innerTracelink)){
			mapArtifactRequirements.put(innerTracelink, new ArrayList<String>());
		}
		mapArtifactRequirements.get(innerTracelink).add(requirement);
	}

	public Map<InnerTracelink, List<String>> getMapArtifactRequirements() {
		return mapArtifactRequirements;
	}

	public void setMapArtifactRequirements(
			Map<InnerTracelink, List<String>> mapArtifactRequirements) {
		this.mapArtifactRequirements = mapArtifactRequirements;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
