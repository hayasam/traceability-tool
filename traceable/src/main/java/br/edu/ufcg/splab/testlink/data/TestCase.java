package br.edu.ufcg.splab.testlink.data;

import java.io.Serializable;
import java.util.List;

public class TestCase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7610815638138322265L;

	private String internalid;

	private String name;

	private List<Keyword> keywords;

	public String getInternalid() {
		return internalid;
	}

	public void setInternalid(String internalid) {
		this.internalid = internalid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append(String.format("<testcase name='%s'>", this.getName()));
		str.append(System.lineSeparator());
		if (keywords != null){
			for (Keyword kw: keywords) {
				str.append(String.format("		%s", kw.toString()));
				str.append(System.lineSeparator());
			}
		}
		str.append("<testcase>");
		return str.toString();
	}
}
