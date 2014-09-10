package splab.ufcg.edu.br.trace.entities;

import java.io.Serializable;

public class InnerTracelink implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6739737102858309554L;

	private String artifactType;

	private String artifact;

	private String semantic;
	
	public InnerTracelink(String artifact, String artifactType, String semantic){
		this.artifact = artifact;
		this.artifactType = artifactType;
		this.semantic = semantic;
	}

	public String getArtifactType() {
		return artifactType;
	}

	public void setArtifactType(String artifactType) {
		this.artifactType = artifactType;
	}

	public String getArtifact() {
		return artifact;
	}

	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}

	public String getSemantic() {
		return semantic;
	}

	public void setSemantic(String semantic) {
		this.semantic = semantic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artifact == null) ? 0 : artifact.hashCode());
		result = prime * result
				+ ((artifactType == null) ? 0 : artifactType.hashCode());
		result = prime * result
				+ ((semantic == null) ? 0 : semantic.hashCode());
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
		InnerTracelink other = (InnerTracelink) obj;
		if (artifact == null) {
			if (other.artifact != null)
				return false;
		} else if (!artifact.equals(other.artifact))
			return false;
		if (artifactType == null) {
			if (other.artifactType != null)
				return false;
		} else if (!artifactType.equals(other.artifactType))
			return false;
		if (semantic == null) {
			if (other.semantic != null)
				return false;
		} else if (!semantic.equals(other.semantic))
			return false;
		return true;
	}
	
	

}
