package splab.ufcg.edu.br.coest.entities;

import java.io.Serializable;

public class AnswerInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8281228626021158134L;

	private String source_artifacts_collection;
	
	private String target_artifacts_collection;

	public String getSource_artifacts_collection() {
		return source_artifacts_collection;
	}

	public void setSource_artifacts_collection(String source_artifacts_collection) {
		this.source_artifacts_collection = source_artifacts_collection;
	}

	public String getTarget_artifacts_collection() {
		return target_artifacts_collection;
	}

	public void setTarget_artifacts_collection(String target_artifacts_collection) {
		this.target_artifacts_collection = target_artifacts_collection;
	}

	public AnswerInfo(){
		
	}
	
}
