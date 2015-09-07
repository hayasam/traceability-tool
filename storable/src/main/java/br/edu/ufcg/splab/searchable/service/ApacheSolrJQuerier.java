package br.edu.ufcg.splab.searchable.service;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.interfaces.Searchable;
import br.edu.ufcg.splab.data.ApacheSolrJFieldEnum;
import br.edu.ufcg.splab.util.StorageSystemProperties;

public class ApacheSolrJQuerier implements Searchable {

	@Inject
	private StorageSystemProperties properties;

	@Override
	public TraceLinkList queryTraceLinks(String query) {
		properties.loadProperties();
		QueryResponse response = null;
		SolrDocumentList results = null;
		TraceLinkList tracelinks = new TraceLinkList();
		try {
			HttpSolrServer server = new HttpSolrServer(
					properties.getConnectionUrl());
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
			throw new RuntimeException(message.toString(), ex);
		} catch (Exception ex) {
			StringBuilder message = new StringBuilder();
			message.append("Unexpected error while querying database");
			throw new RuntimeException(message.toString(), ex);
		}
		return tracelinks;
	}

	@SuppressWarnings("unchecked")
	private TraceLinkList getTraceLinksFromQueryResult(SolrDocumentList results) {
		TraceLinkList tracelinks = new TraceLinkList();

		if (results == null) {
			return tracelinks;
		}

		Iterator<SolrDocument> resultIterator = results.iterator();
		while (resultIterator.hasNext()) {
			SolrDocument document = resultIterator.next();
			List<String> requirement = (List<String>) document
					.getFieldValue(ApacheSolrJFieldEnum.REQUIREMENT.toString());
			String semantic = document.getFieldValue(
					ApacheSolrJFieldEnum.SEMANTIC.toString()).toString();
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
