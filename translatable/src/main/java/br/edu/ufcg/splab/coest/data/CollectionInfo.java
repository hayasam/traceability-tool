package br.edu.ufcg.splab.coest.data;

import java.io.Serializable;


public class CollectionInfo implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7676342558652652402L;

	private String id;
	
	private String name;
	
	private String version;
	
	private String description;
	
	private String content_location;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent_location() {
		return content_location;
	}

	public void setContent_location(String content_location) {
		this.content_location = content_location;
	}
	
	public CollectionInfo(){
		
	}

}
