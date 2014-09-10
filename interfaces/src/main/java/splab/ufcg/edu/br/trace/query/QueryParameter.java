package splab.ufcg.edu.br.trace.query;
import splab.ufcg.edu.br.trace.query.QueryParameterTypeEnum;

import java.io.Serializable;

public class QueryParameter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1536165358271932730L;

	private QueryParameterTypeEnum field;
	
	private String value;

	public QueryParameterTypeEnum getField() {
		return field;
	}

	public void setField(QueryParameterTypeEnum field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	public QueryParameter(QueryParameterTypeEnum field, String value) {
		this.field = field;
		this.value = value;
	}

	@Override
	public String toString() {
		
		return String.format("%s %s", field.toString(), value);
	}
	

}
