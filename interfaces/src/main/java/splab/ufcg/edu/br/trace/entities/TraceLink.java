package splab.ufcg.edu.br.trace.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 


/**
 * Class that represents a TraceLink A trace link has a key, a source and an
 * artifact or document
 * 
 * examples: ['REQ01', 'Overlaps', 'Source Code',  'Login.java'] ['UC25', 'Test
 * Case', 'Realizes', 'TC034 Validate persons data']
 * 
 * @author Arthur
 */
public class TraceLink implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6213141720858774745L;

	private String requirement;

	private String artifactType;

	private String artifact;

	private String semantic;

	private List<String> requirements;

	public TraceLink(){
		requirements = new ArrayList<String>();
	}
	
	public TraceLink(String requirement, String semantic, String artifactType, String artifact) {
		this();
		this.checkParameters(requirement, artifactType, artifact, semantic);
		this.requirement = requirement;
		this.artifactType = artifactType;
		this.artifact = artifact;
		this.semantic = semantic;
	}

	public TraceLink(List<String>  requirements, String semantic , String type,
			String artifact) {
		this();
		this.requirements = requirements;
		this.artifactType = type;
		this.artifact = artifact;
		this.semantic = semantic;
	}

	public void checkParameters(String requirement, String artifactType,
			String artifact, String semantic)  {
		
		if (checkNullParameters(requirement, artifactType, artifact, semantic)) {
			String message = this.buildExceptionMessage(requirement, artifactType, artifact, semantic, "NULL");
			throw new RuntimeException(message);
		}
		if (checkEmptyParameters(requirement, artifactType, artifact, semantic)) {
			String message = this.buildExceptionMessage(requirement, artifactType, artifact, semantic, "EMPTY");
			throw new RuntimeException(message);
		}
	}

	private boolean checkEmptyParameters(String requirement,
			String artifactType, String artifact, String semantic) {
		return requirement.isEmpty() || artifactType.isEmpty() || artifact.isEmpty() || semantic.isEmpty();
	}

	private String buildExceptionMessage(String requirement,
			String artifactType, String artifact, String semantic,
			String exception) {
		StringBuilder message = new StringBuilder();
		message.append("Tracelink parameters cannot be: ");
		message.append(exception);
		message.append("Requiremen: [");
		message.append(requirement);
		message.append("] - Semantic: [");
		message.append(semantic);
		message.append("] - Artifact type: [");
		message.append(artifactType);
		message.append("] - Artifact: [");
		message.append(artifact);
		message.append("]");

		return message.toString();
	}

	private boolean checkNullParameters(String requirement, String artifactType,
			String artifact, String semantic) {
		return requirement == null || artifactType == null || artifact == null
				|| semantic == null;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
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
				+ ((requirement == null) ? 0 : requirement.hashCode());
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
		TraceLink other = (TraceLink) obj;
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
		if (requirement == null) {
			if (other.requirement != null)
				return false;
		} else if (!requirement.equals(other.requirement))
			return false;
		if (semantic == null) {
			if (other.semantic != null)
				return false;
		} else if (!semantic.equals(other.semantic))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append("(");
		result.append(requirements);
		result.append(", ");
		result.append(semantic);
		result.append(", ");
		result.append(artifactType);
		result.append(", ");		
		result.append(artifact);
		result.append(")");
		return result.toString();
	}

	public void checkParameters() {
		this.checkParameters(this.requirement, this.artifactType,
				this.artifact, this.semantic);
	}

	public List<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}
}
