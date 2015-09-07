package br.edu.ufcg.splab.searchable.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import splab.ufcg.edu.br.trace.entities.TraceQuery;
import splab.ufcg.edu.br.trace.query.QueryParameter;
import splab.ufcg.edu.br.trace.query.QueryParameterTypeEnum;
import br.edu.ufcg.splab.data.ApacheSolrJFieldEnum;

public class ApacheSolrjQueryConversor implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 190514415966567983L;
	
	private TraceQuery query;
	
	private Map<QueryParameterTypeEnum, ApacheSolrJFieldEnum> mapQueryParameterField;

	private Map<String, QueryParameterTypeEnum> mapQueryParameterValueField;

	public ApacheSolrjQueryConversor(TraceQuery query) {
		this.query = query;
		
		this.mapQueryParameterField = new HashMap<QueryParameterTypeEnum, ApacheSolrJFieldEnum>();
		this.mapQueryParameterValueField = new HashMap<String, QueryParameterTypeEnum>();
		this.populateMap();
	}
	
	private void populateMap() {
		populateFieldMap();
		populateValueMap();
	}

	private void populateValueMap() {
		if (hasIterableParameters()){
			for (QueryParameter param : query.getParameters().getParameterList()) {
				mapQueryParameterValueField.put(param.getValue(), param.getField());
			}
		}
	}

	private boolean hasIterableParameters() {
		return query.getParameters() != null && !query.getParameters().isEmpty() && query.getParameters().getParameterList() != null && !query.getParameters().getParameterList().isEmpty();
	}

	private void populateFieldMap() {
		this.mapQueryParameterField.put(QueryParameterTypeEnum.ARTIFACT, ApacheSolrJFieldEnum.ARTIFACT);
		this.mapQueryParameterField.put(QueryParameterTypeEnum.ARTIFACT_TYPE, ApacheSolrJFieldEnum.ARTIFACT_TYPE);
		this.mapQueryParameterField.put(QueryParameterTypeEnum.SEMANTIC, ApacheSolrJFieldEnum.SEMANTIC);
		this.mapQueryParameterField.put(QueryParameterTypeEnum.REQUIREMENT, ApacheSolrJFieldEnum.REQUIREMENT);
	}

	public String convert(){
		StringBuilder str = new StringBuilder();
		
		return str.toString();
	}

}
