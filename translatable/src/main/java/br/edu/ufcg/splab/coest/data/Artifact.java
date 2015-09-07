package br.edu.ufcg.splab.coest.data;

import java.io.Serializable;

public class Artifact implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7207895568296304669L;

	private String id;
	
	private String content;
	
	private String parent_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	

	public Artifact(){
		
	}

}
