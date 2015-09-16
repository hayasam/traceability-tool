package br.edu.ufcg.splab.testlink.data;

import java.io.Serializable;

public class Keyword implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4506541034026652648L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append(String.format("<keyword name='%s'>", this.getName()));
		return str.toString();
	}
}
