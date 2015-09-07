package br.edu.ufcg.splab.coest.data;

import java.io.Serializable;

public class Link implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -114855498371985782L;
	
	private String source_artifact_id;
	
	private String target_artifact_id;

	public String getSource_artifact_id() {
		return source_artifact_id;
	}

	public void setSource_artifact_id(String source_artifact_id) {
		this.source_artifact_id = source_artifact_id;
	}

	public String getTarget_artifact_id() {
		return target_artifact_id;
	}

	public void setTarget_artifact_id(String target_artifact_id) {
		this.target_artifact_id = target_artifact_id;
	}
	
	
	public Link(){
		
	}

	
	
}
