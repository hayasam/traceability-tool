/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package splab.ufcg.edu.br.trace.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import splab.ufcg.edu.br.trace.query.QueryExpression;
import splab.ufcg.edu.br.trace.query.QueryParameter;
import splab.ufcg.edu.br.trace.query.QueryParameterList;
import splab.ufcg.edu.br.trace.query.QueryParameterTypeEnum;

/**
 * 
 * @author Arthur
 */
public class TraceQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 807107927473819076L;

	private String name;

	private QueryParameterList parameters;

	private QueryExpression queryExpression;

	public TraceQuery(String queryName, QueryParameterList parameters,
			QueryExpression expression) {
		this.name = queryName;
		this.parameters = parameters;
		this.queryExpression = expression;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public QueryParameterList getParameters() {
		return parameters;
	}

	public void setParameters(QueryParameterList parameters) {
		this.parameters = parameters;
	}

	public QueryExpression getQueryExpression() {
		return queryExpression;
	}

	public void setQueryExpression(QueryExpression queryExpression) {
		this.queryExpression = queryExpression;
	}

	@Override
	public String toString() {

		StringBuilder str = new StringBuilder();
		str.append(this.getName());
		if (parameters != null) {
			str.append(System.lineSeparator());
			str.append(parameters.toString());
		}
		if (queryExpression != null) {
			str.append(System.lineSeparator());
			str.append(queryExpression.toString());
		}

		return str.toString();
	}

	public List<QueryParameter> getParametersList() {
		if (parameters != null) {
			return parameters.getParameterList();

		}
		return new LinkedList<QueryParameter>();
	}

	public String getExpression() {

		String query = "";
		if (queryExpression != null) {
			query = queryExpression.toString();

		}
		
		if (parameters != null) {
			Map<String, QueryParameterTypeEnum> valueMap = parameters.getParameterValueMap();
			
			for(String param: valueMap.keySet()){
				query = query.replace(param, String.format("%s:<value>", valueMap.get(param)));
			}
			
		}

		return query;
	}
	
	public QueryParameterTypeEnum getTypeOfParameter(String param) {
		Map<String, QueryParameterTypeEnum> valueMap = parameters.getParameterValueMap();
		
		if (valueMap.containsKey(param)){
			return valueMap.get(param);
		} else {
			return QueryParameterTypeEnum.DEFAULT;
		}
		
	}

}
