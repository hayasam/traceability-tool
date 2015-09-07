package br.edu.ufcg.splab.indexable.service;

public class SolrJID {
	
	private static SolrJID instance;
	
	private long currentId;
	
	private SolrJID(){
		currentId = new Long(0);
		
	}
	
	public static SolrJID getInstance(){
		if (instance == null){
			instance = new SolrJID();
		}
		
		return instance;
	}
	
	public long getNextId(){		
		return ++this.currentId;		
	}
	
	public long getLastId(){
		return this.currentId;
	}
}
