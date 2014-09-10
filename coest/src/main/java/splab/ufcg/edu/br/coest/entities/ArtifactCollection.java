package splab.ufcg.edu.br.coest.entities;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "artifacts_collection")
@XmlSeeAlso({ 	splab.ufcg.edu.br.coest.entities.CollectionInfo.class, 
				splab.ufcg.edu.br.coest.entities.Artifact.class })
@XmlAccessorType(XmlAccessType.FIELD)
public class ArtifactCollection implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5278360293318537747L;

	@XmlElement
	private CollectionInfo collection_info;
	
	@XmlElementWrapper
    @XmlElement(name = "artifact")
	private List<Artifact> artifacts;
	
	public ArtifactCollection(){
		
	}

	public CollectionInfo getCollection_info() {
		
		return collection_info;
	}

	public void setCollection_info(CollectionInfo collection_info) {
		this.collection_info = collection_info;
	}

	public List<Artifact> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(List<Artifact> artifacts) {
		this.artifacts = artifacts;
	}

	
}


