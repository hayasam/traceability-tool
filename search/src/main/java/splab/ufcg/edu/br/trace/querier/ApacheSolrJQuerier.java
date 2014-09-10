package splab.ufcg.edu.br.trace.querier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import splab.ufcg.edu.br.trace.entities.ApacheSolrJFieldEnum;
import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.entities.TraceQuery;
import splab.ufcg.edu.br.trace.exceptions.IndexException;
import splab.ufcg.edu.br.trace.exceptions.QueryException;
import splab.ufcg.edu.br.trace.exceptions.TraceLinkException;
import splab.ufcg.edu.br.trace.interfaces.Searchable;
import splab.ufcg.edu.br.trace.search.util.ConnectionProperties;

public class ApacheSolrJQuerier implements Searchable {

	private Properties properties;
	
	private final String URL_PROPERTIE = "apache.url";
	private final String CORE_PROPERTIE = "apache.core";


	public ApacheSolrJQuerier() {

	}

	public ApacheSolrJQuerier(Properties properties) {

		this.properties = properties;
	}



	@Override
	public TraceLinkList queryTraceLinks(String query)
			throws QueryException {

		QueryResponse response = null;
		SolrDocumentList results = null;
		TraceLinkList tracelinks = new TraceLinkList();

		try {
			HttpSolrServer server = new HttpSolrServer(
					this.getQuerierConnectionUrl());

			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setQuery(query);

			solrQuery.setFields(ApacheSolrJFieldEnum.REQUIREMENT.toString(),
					ApacheSolrJFieldEnum.SEMANTIC.toString(),
					ApacheSolrJFieldEnum.ARTIFACT_TYPE.toString(),
					ApacheSolrJFieldEnum.ARTIFACT.toString());

			solrQuery.setStart(0);
			solrQuery.set("defType", "edismax");
			solrQuery.setRows(1000000);
			response = server.query(solrQuery);
			results = response.getResults();
			tracelinks = this.getTraceLinksFromQueryResult(results);
			
		} catch (SolrServerException ex) {
			StringBuilder message = new StringBuilder();
			message.append("Unexpected error while querying database");

			throw new QueryException(message.toString(), ex);
		} catch (TraceLinkException ex) {

			StringBuilder message = new StringBuilder();
			message.append("Unexpected error while querying database");

			throw new QueryException(message.toString(), ex);

		}

		return tracelinks;
	}

	private String getQuerierConnectionUrl() throws QueryException {

			this.checkProperties();

			StringBuilder urlConn = new StringBuilder();

			urlConn.append(properties.get(URL_PROPERTIE));
			urlConn.append(properties.get(CORE_PROPERTIE));

			return urlConn.toString();


	}

	private void checkProperties() throws QueryException {
		if (this.properties == null) {
			throw new QueryException("Properties file not set.");
		}
		
	}

	private TraceLinkList getTraceLinksFromQueryResult(SolrDocumentList results)
			throws TraceLinkException {

		TraceLinkList tracelinks = new TraceLinkList();

		if (results == null) {
			return tracelinks;
		}

		Iterator<SolrDocument> resultIterator = results.iterator();
		while (resultIterator.hasNext()) {
			SolrDocument document = resultIterator.next();
			List<String> requirement = (List<String>) document.getFieldValue(
					ApacheSolrJFieldEnum.REQUIREMENT.toString());
			String semantic = document.getFieldValue(ApacheSolrJFieldEnum.SEMANTIC.toString()).toString();
			String type = document.getFieldValue(
					ApacheSolrJFieldEnum.ARTIFACT_TYPE.toString()).toString();
			String artifact = document.getFieldValue(
					ApacheSolrJFieldEnum.ARTIFACT.toString()).toString();

			TraceLink traceLink = new TraceLink(requirement, semantic, type,
					artifact);

			tracelinks.add(traceLink);

		}

		return tracelinks;
	}



}
