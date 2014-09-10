package splab.ufcg.edu.br.trace.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QueryParameterList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5251910908103420909L;	
	
	
	private QueryParameter paramenter;
	
	private QueryParameterList nextParameter;
	
	
	
	public QueryParameterList(){
		paramenter = null;
		nextParameter = null;
	}
	
	public QueryParameterList(QueryParameter current, QueryParameterList next){
		this();
		this.paramenter = current;
		this.nextParameter = next;
	}

	public QueryParameter getParamenter() {
		return paramenter;
	}

	public void setParamenter(QueryParameter paramenter) {
		this.paramenter = paramenter;
	}

	public QueryParameterList getNextParameter() {
		return nextParameter;
	}

	public void setNextParameter(QueryParameterList nextParameter) {
		this.nextParameter = nextParameter;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		if (paramenter != null){
			str.append(paramenter.toString());
			
			if(nextParameter != null){
				str.append(", ");
				str.append(nextParameter.toString());
			}			
		}
		

		
		return str.toString();
	}

	public boolean isEmpty() {

		if (paramenter != null) {
			return false;
		}
		
		return true;
	}

	public List<QueryParameter> getParameterList() {
		
		List<QueryParameter> result = new LinkedList<QueryParameter>();
		
		if (paramenter != null){
			result.add(paramenter);
		}
		
		QueryParameterList nextParameterNode = this.nextParameter;
			
		while (nextParameterNode != null){
			result.add(nextParameterNode.getParamenter());
			nextParameterNode = nextParameterNode.nextParameter;
		}		
		
		return result;
	}

	public Map<String, QueryParameterTypeEnum> getParameterValueMap() {
		
		Map<String, QueryParameterTypeEnum> map = new HashMap<String, QueryParameterTypeEnum>();
		List<QueryParameter> parameterList = this.getParameterList();
		
		for (QueryParameter queryParameter : parameterList) {
			map.put(queryParameter.getValue(), queryParameter.getField());
		}

		return map;
	}
	
	
}
