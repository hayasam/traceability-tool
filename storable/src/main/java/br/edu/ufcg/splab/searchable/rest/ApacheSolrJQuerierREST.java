package br.edu.ufcg.splab.searchable.rest;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import br.edu.ufcg.splab.searchable.service.ApacheSolrJQuerier;

@Path("/search")
public class ApacheSolrJQuerierREST {
	
	@Inject
	private ApacheSolrJQuerier querier;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchTraceLinks(String query) {
		Response.ResponseBuilder builder = null;
		try {
			TraceLinkList extractedTracelinks = querier.queryTraceLinks(query);
			builder = Response.ok().entity(extractedTracelinks);
			builder.build();
		} catch (RuntimeException ex) {
			Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj);
	  	}catch (Exception ex) {
			Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj);
		}
		return builder.build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIndexTraceLinks() {
		Response.ResponseBuilder builder = null;
		try {
			builder = Response.ok().entity("Search");
			builder.build();
		} catch (RuntimeException ex) {
			Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj);
	  	}catch (Exception ex) {
			Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj);
		}
		return builder.build();
	}
	
}
