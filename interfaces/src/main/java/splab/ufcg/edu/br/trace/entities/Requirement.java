
package splab.ufcg.edu.br.trace.entities;

import java.io.Serializable;

public class Requirement implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6408800390710541681L;

	private String ID;
	
	private String description;
	
	public Requirement(String ID){
		this.ID = ID;
		
	}
	
	public Requirement(String ID, String description) {
		this(ID);
		this.description = description;
		
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
