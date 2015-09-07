package br.edu.ufcg.splab.indexable.service;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import splab.ufcg.edu.br.trace.entities.InnerTracelink;
import splab.ufcg.edu.br.trace.entities.MappedTracelink;
import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.interfaces.Indexable;
import br.edu.ufcg.splab.data.ApacheSolrJFieldEnum;
import br.edu.ufcg.splab.util.ExecutionMode;
import br.edu.ufcg.splab.util.StorageSystemProperties;

public class ApacheSolrJIndexer implements Indexable {

	private static final String DELETE_ALL_INDEXES = "*:*";

	private static final long COMMIT_LOT_SIZE = 1000;

	private TraceLinkList tracelinkList;

	private MappedTracelink map;

	@Inject
	private StorageSystemProperties properties;

	@Override
	public void indexTraceLinks(File directory) {
		this.properties.loadProperties();
		this.tracelinkList = new TraceLinkList();
		this.map = new MappedTracelink();

		try {
			if (ExecutionMode.FULL.equals(properties.getIndexMode())) {
				this.clearIndexDatabase();

				this.importTraceLinks(directory);
			} else {
				throw new RuntimeException("Not yet implemented");
			}
		} catch (SolrServerException ex) {

			StringBuilder message = new StringBuilder();
			message.append("Could not establish connection with Solr server: ");
			message.append(properties.getConnectionUrl());

			throw new RuntimeException(message.toString(), ex);
		} catch (Exception ex) {

			StringBuilder message = new StringBuilder();
			message.append("Unexpected error while indexing tracelinks: ");
			message.append(ex.getMessage());

			throw new RuntimeException(message.toString(), ex);
		}
	}

	private void importTraceLinks(File directory) throws Exception {
//		this.tracelinkList = new TraceLinkList();
//		if (directory.exists()) {
//
//			File[] fileList = directory.listFiles();
//
//			for (File file : fileList) {
//				traceLinkWriter = new TraceLinkWriter();
//
//				TraceLinkList extractedTraceLinks = traceLinkWriter.read(file);
//				this.tracelinkList.addAll(extractedTraceLinks);
//
//			}
//			if (canIndex()) {
//				this.setUpMappedTracelinks();
//				this.indexImportedTraceLinks();
//			}
//		} else {
//
//			StringBuilder message = new StringBuilder();
//			message.append("Could not find directory to import trace links: ");
//			message.append(properties.getIndexDirectory());
//
//			throw new RuntimeException(message.toString());
//		}
	}

	private void setUpMappedTracelinks() {
		for (TraceLink trace : this.tracelinkList.getTraceLinks()) {
			InnerTracelink artifact = new InnerTracelink(trace.getArtifact(),
					trace.getArtifactType(), trace.getSemantic());
			this.map.put(artifact, trace.getRequirement());
		}
	}

	private void indexImportedTraceLinks() throws SolrServerException,
			Exception {

		HttpSolrServer server = new HttpSolrServer(
				properties.getConnectionUrl());

		long count = 0;
		for (InnerTracelink tracelink : this.map.getMapArtifactRequirements()
				.keySet()) {

			SolrInputDocument doc = this.getDocummentFromTraceLink(tracelink);

			if (count % COMMIT_LOT_SIZE == 0) {
				server.commit();
			}
			server.add(doc);
			count++;
		}
		server.commit();
	}

	private SolrInputDocument getDocummentFromTraceLink(InnerTracelink tracelink) {
		long currentID = SolrJID.getInstance().getNextId();
		SolrInputDocument doc = new SolrInputDocument();

		doc.addField(ApacheSolrJFieldEnum.ID.toString(), currentID);

		doc.addField(ApacheSolrJFieldEnum.SEMANTIC.toString(),
				tracelink.getSemantic());
		doc.addField(ApacheSolrJFieldEnum.ARTIFACT_TYPE.toString(),
				tracelink.getArtifactType());
		doc.addField(ApacheSolrJFieldEnum.ARTIFACT.toString(),
				tracelink.getArtifact());

		List<String> relatedRequirements = this.map
				.getMapArtifactRequirements().get(tracelink);
		doc.addField(ApacheSolrJFieldEnum.REQUIREMENT.toString(),
				relatedRequirements);

		return doc;
	}

	@Override
	public void clearIndexDatabase() {

		try {
			HttpSolrServer server = new HttpSolrServer(
					properties.getConnectionUrl());

			server.deleteByQuery(DELETE_ALL_INDEXES);

			server.commit();

		} catch (SolrServerException ex) {
			StringBuilder message = new StringBuilder();
			message.append("Could not establish connection with Solr server: ");
			message.append(properties.getConnectionUrl());

			throw new RuntimeException(message.toString(), ex);

		} catch (Exception ex) {

			StringBuilder message = new StringBuilder();
			message.append("Unexpected error while purging index database");

			throw new RuntimeException(message.toString(), ex);
		}
	}

	private boolean canIndex() {
		return tracelinkList != null & tracelinkList.getTraceLinks() != null
				&& tracelinkList.getTraceLinks().size() > 0;
	}

	public boolean isEmpty() {

		QueryResponse response;

		try {
			HttpSolrServer server = new HttpSolrServer(
					properties.getConnectionUrl());

			SolrQuery query = new SolrQuery();
			query.setQuery("*:*");
			query.setStart(0);
			query.set("defType", "edismax");
			response = server.query(query);
		} catch (SolrServerException ex) {
			StringBuilder message = new StringBuilder();
			message.append("Unexpected error while purging index database");

			throw new RuntimeException(message.toString(), ex);
		}
		SolrDocumentList results = response.getResults();
		return results.isEmpty();
	}

	@Override
	public void indexTraceLinks(TraceLinkList tracelinkList) {
		this.properties.loadProperties();
		this.tracelinkList = new TraceLinkList();
		this.map = new MappedTracelink();
		try {
			this.clearIndexDatabase();
			this.tracelinkList = tracelinkList;

			if (canIndex()) {
				this.setUpMappedTracelinks();
				this.indexImportedTraceLinks();
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
