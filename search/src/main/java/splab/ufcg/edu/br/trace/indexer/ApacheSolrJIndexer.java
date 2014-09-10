package splab.ufcg.edu.br.trace.indexer;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import splab.ufcg.edu.br.trace.entities.ApacheSolrJFieldEnum;
import splab.ufcg.edu.br.trace.entities.InnerTracelink;
import splab.ufcg.edu.br.trace.entities.MappedTracelink;
import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.exceptions.IndexException;
import splab.ufcg.edu.br.trace.interfaces.Indexable;
import splab.ufcg.edu.br.trace.interfaces.Translatable;
import splab.ufcg.edu.br.trace.search.util.ConnectionProperties;
import splab.ufcg.edu.br.trace.search.util.ExecutionMode;
import splab.ufcg.edu.br.trace.search.util.IndexerProperties;
import splab.ufcg.edu.br.trace.writer.TraceLinkWriter;

public class ApacheSolrJIndexer implements Indexable {

	private static final String DELETE_ALL_INDEXES = "*:*";

	private static final long COMMIT_LOT_SIZE = 1000;

	private final String MODE_PROPERTIE = "indexer.mode";
	private final String DIRECTORY_PROPERTIE = "indexer.directory";

	private final String URL_PROPERTIE = "apache.url";
	private final String CORE_PROPERTIE = "apache.core";

	private Translatable traceLinkWriter;

	private TraceLinkList tracelinkList;
	
	private Properties properties;
	
	private MappedTracelink map;


	public ApacheSolrJIndexer() {
		this.map = new MappedTracelink();
	}

	public ApacheSolrJIndexer(Properties properties) {
		this();
		this.properties = properties;
	}

	@Override
	public void indexTraceLinks(File directory) throws IndexException {

		try {
			if (ExecutionMode.FULL.equals(this.getIndexMode())) {
				this.clearIndexDatabase();

				this.importTraceLinks(directory);
			} else {
				throw new IndexException("Not yet implemented");
			}
		} catch (SolrServerException ex) {

			StringBuilder message = new StringBuilder();
			message.append("Could not establish connection with Solr server: ");
			message.append(this.getIndexConnectionUrl());

			throw new IndexException(message.toString(), ex);
		} catch (Exception ex) {

			StringBuilder message = new StringBuilder();
			message.append("Unexpected error while indexing tracelinks: ");
			message.append(ex.getMessage());

			throw new IndexException(message.toString(), ex);
		}
	}

	private ExecutionMode getIndexMode() throws IndexException {
		this.checkProperties();
		
		String mode = properties.getProperty(MODE_PROPERTIE);
		
		return ExecutionMode.getEnum(mode);
	}

	private void checkProperties() throws IndexException {
		if (this.properties == null) {
			throw new IndexException("Properties file not set.");
		}
	}

	private void importTraceLinks(File directory) throws Exception {

		this.tracelinkList = new TraceLinkList();
		if (directory.exists()) {

			File[] fileList = directory.listFiles();

			for (File file : fileList) {
				traceLinkWriter = new TraceLinkWriter();

				 TraceLinkList extractedTraceLinks = traceLinkWriter.read(file);
				 this.tracelinkList.addAll(extractedTraceLinks);

			}
			if (canIndex()) {
				this.setUpMappedTracelinks();
				this.indexImportedTraceLinks();
			}
		} else {

			StringBuilder message = new StringBuilder();
			message.append("Could not find directory to import trace links: ");
			message.append(this.getIndexDirectory());

			throw new IndexException(message.toString());
		}
	}

	private void setUpMappedTracelinks() {
		for (TraceLink trace : this.tracelinkList.getTraceLinks()) {
			InnerTracelink artifact = new InnerTracelink(trace.getArtifact(), trace.getArtifactType(), trace.getSemantic());
			this.map.put(artifact, trace.getRequirement());
		}
	}

	private String getIndexDirectory() throws IndexException {
		this.checkProperties();
		return properties.getProperty(DIRECTORY_PROPERTIE);
	}

	private void indexImportedTraceLinks() throws SolrServerException,
			Exception {

		HttpSolrServer server = new HttpSolrServer(this.getIndexConnectionUrl());

		long count = 0;
		for (InnerTracelink tracelink : this.map.getMapArtifactRequirements().keySet()) {

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
		
		
		List<String> relatedRequirements = this.map.getMapArtifactRequirements().get(tracelink);
		doc.addField(ApacheSolrJFieldEnum.REQUIREMENT.toString(), relatedRequirements);

		return doc;
	}

	@Override
	public void clearIndexDatabase() throws IndexException {

		try {
			HttpSolrServer server = new HttpSolrServer(
					this.getIndexConnectionUrl());

			server.deleteByQuery(DELETE_ALL_INDEXES);

			server.commit();

		} catch (SolrServerException ex) {
			StringBuilder message = new StringBuilder();
			message.append("Could not establish connection with Solr server: ");
			message.append(this.getIndexConnectionUrl());

			throw new IndexException(message.toString(), ex);

		} catch (Exception ex) {

			StringBuilder message = new StringBuilder();
			message.append("Unexpected error while purging index database");

			throw new IndexException(message.toString(), ex);
		}
	}

	private boolean canIndex() {
		return tracelinkList != null & tracelinkList.getTraceLinks() != null
				&& tracelinkList.getTraceLinks().size() > 0;
	}

	public boolean isEmpty() throws IndexException {

		QueryResponse response;

		try {
			HttpSolrServer server = new HttpSolrServer(
					this.getIndexConnectionUrl());

			SolrQuery query = new SolrQuery();
			query.setQuery("*:*");
			query.setStart(0);
			query.set("defType", "edismax");
			response = server.query(query);
		} catch (SolrServerException ex) {
			StringBuilder message = new StringBuilder();
			message.append("Unexpected error while purging index database");

			throw new IndexException(message.toString(), ex);
		}
		SolrDocumentList results = response.getResults();

		return results.isEmpty();
	}

	private String getIndexConnectionUrl() throws IndexException {

		this.checkProperties();

		StringBuilder urlConn = new StringBuilder();

		urlConn.append(properties.get(URL_PROPERTIE));
		urlConn.append(properties.get(CORE_PROPERTIE));

		return urlConn.toString();

	}
	
	public Translatable getTraceLinkWriter() {
		return traceLinkWriter;
	}

	public void setTraceLinkWriter(Translatable traceLinkWriter) {
		this.traceLinkWriter = traceLinkWriter;
	}

	@Override
	public void indexTraceLinks(TraceLinkList tracelinkList)
			throws IndexException {
		// TODO Auto-generated method stub

		try{
			this.clearIndexDatabase();
			this.tracelinkList = tracelinkList;

			if (canIndex()) {
				this.setUpMappedTracelinks();
				this.indexImportedTraceLinks();
			}
			
		} catch (Exception ex) {
			
		}
	}


}
