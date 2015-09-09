package br.edu.ufcg.splab.testlink.service;

import javax.inject.Inject;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.interfaces.Traceable;
import br.edu.ufcg.splab.testlink.data.TestLinkDAO;


public class TestLinkService implements Traceable {

	@Inject
	private TestLinkDAO dao;

	public TraceLinkList getTraceLinks() {		
		return dao.getTraceLinks();
	}
}
