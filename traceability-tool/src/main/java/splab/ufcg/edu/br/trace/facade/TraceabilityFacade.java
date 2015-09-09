package splab.ufcg.edu.br.trace.facade;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.util.JsonContentTypeResponseFilter;

public class TraceabilityFacade implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SEARCH_SERVICE = "service.searchable";

	private static final String INDEX_SERVICE = "service.indexable";

	private static final String TRANSLATE_SERVICE = "service.translatable";

	private Properties properties;

	private TraceLinkList tracelinkList;

	private static TraceabilityFacade instance;

	public TraceabilityFacade() {
		try {

			this.properties = new Properties();
			InputStream inputStream = getClass().getResourceAsStream(
					"/trace-tool.properties");

			this.properties.load(inputStream);

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

	public TraceLinkList readTraceLinks() {
		return this.tracelinkList;
	}

	public TraceLinkList getTraceLinkList() {
		return this.tracelinkList;
	}

	public TraceLinkList queryTraceLinks(String query) {

		TraceLinkList tracelinks = null;

		try {

			String caminho = this.properties.getProperty(SEARCH_SERVICE);
			URL endereco;

			endereco = new URL(caminho);

			Client client = ClientBuilder.newClient();
			WebTarget target;
			target = client.target(URI.create(endereco.toExternalForm()));
			client.register(JsonContentTypeResponseFilter.class);
			Builder builder = target.request();
			Entity<String> entity = Entity.json(query);
			Response response = builder.post(entity);

			if (response.getStatus() == HttpStatus.SC_OK) {
				tracelinks = response.readEntity(TraceLinkList.class);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return tracelinks;
	}

	public boolean indexTraceLinks() {
		try {

			String caminho = this.properties.getProperty(INDEX_SERVICE);
			URL endereco;

			endereco = new URL(caminho);

			Client client = ClientBuilder.newClient();
			client.register(JsonContentTypeResponseFilter.class);
			WebTarget target;
			target = client.target(URI.create(endereco.toExternalForm()));
			Builder builder = target.request();
			Entity<TraceLinkList> entity = Entity.json(this.tracelinkList);
			Response response = builder.post(entity);
			if (response.getStatus() != HttpStatus.SC_OK) {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean extractTraceLinks() {
		try {
			String caminho = this.properties.getProperty(TRANSLATE_SERVICE);
			URL endereco;

			endereco = new URL(caminho);

			Client client = ClientBuilder.newClient();
			client.register(JsonContentTypeResponseFilter.class);
			WebTarget target;
			target = client.target(URI.create(endereco.toExternalForm()));
			Builder builder = target.request();
			Response response = builder.get();
			if (response.getStatus() == HttpStatus.SC_OK) {
				String responseBody = response.readEntity(String.class);
				this.tracelinkList = new ObjectMapper().readValue(responseBody, TraceLinkList.class);
				this.writeTraceLinks();
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	private void writeTraceLinks() {
		try{
			String caminho = this.properties.getProperty(TRANSLATE_SERVICE);
			URL endereco;

			endereco = new URL(caminho);

			Client client = ClientBuilder.newClient();
			client.register(JsonContentTypeResponseFilter.class);
			WebTarget target;
			target = client.target(URI.create(endereco.toExternalForm()));
			Builder builder = target.request();
			Entity<TraceLinkList> entity = Entity.json(this.tracelinkList);
			builder.post(entity);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
