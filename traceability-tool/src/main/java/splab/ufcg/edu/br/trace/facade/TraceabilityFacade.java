package splab.ufcg.edu.br.trace.facade;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import splab.ufcg.edu.br.coest.writer.CoestTranslator;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.indexer.ApacheSolrJIndexer;
import splab.ufcg.edu.br.trace.interfaces.Indexable;
import splab.ufcg.edu.br.trace.interfaces.Searchable;
import splab.ufcg.edu.br.trace.interfaces.Traceable;
import splab.ufcg.edu.br.trace.interfaces.Translatable;
import splab.ufcg.edu.br.trace.querier.ApacheSolrJQuerier;
import splab.ufcg.edu.br.trace.translator.TestlinkTranslator;
import splab.ufcg.edu.br.trace.writer.TraceLinkWriter;

public class TraceabilityFacade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String WRITER_DIRECTORY = "writer.directory";

	private static final String INDEXER_DIRECTORY = "indexer.directory";

	private Translatable writer;

	private Searchable search;

	private Indexable indexer;

	private Traceable traceable;

	private TraceLinkList tracelinkList;

	private Properties properties;

	private static TraceabilityFacade instance;

	public TraceabilityFacade() {
		try {

			this.properties = new Properties();
			InputStream inputStream = getClass().getResourceAsStream(
					"/trace-tool.properties");

			this.properties.load(inputStream);



			this.writer = new TraceLinkWriter();
			this.search = new ApacheSolrJQuerier(properties);
			this.indexer = new ApacheSolrJIndexer(properties);
			 

//			this.traceable = new TestlinkTranslator(properties);

			this.traceable = new CoestTranslator(properties);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static TraceabilityFacade getInstance() {
		if (instance == null) {
			instance = new TraceabilityFacade();
		}
		return instance;
	}

	public boolean indexTraceLinks() {

		try {

			File directory = new File(properties.getProperty(INDEXER_DIRECTORY));
			this.indexer.indexTraceLinks(this.tracelinkList);
		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}

		return true;
	}

	public boolean extractTraceLinks() {
		try {

			TraceLinkList traceLinks = this.traceable.getTraceLinks();


			File file = new File(properties.getProperty(WRITER_DIRECTORY));
			this.writer.write(file, traceLinks);
			
			this.tracelinkList = traceLinks;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public TraceLinkList readTraceLinks() {
	
			return this.tracelinkList;

		
	}

	public TraceLinkList queryTraceLinks(String query) {

		TraceLinkList tracelinks = null;

		try {
			tracelinks = this.search.queryTraceLinks(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return tracelinks;
	}

	public TraceLinkList getTraceLinkList() {
		return this.tracelinkList;
	}

}
