package br.edu.ufcg.splab.coest.service;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import br.edu.ufcg.splab.coest.data.AnswerSet;
import br.edu.ufcg.splab.coest.data.Link;
import br.edu.ufcg.splab.coest.data.MappedArtifacts;

public class CoestTraceLinksReader {

	public TraceLinkList read(File file, MappedArtifacts mappedArtifacts)
			throws Exception {

		AnswerSet links = null;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(AnswerSet.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			links = (AnswerSet) jaxbUnmarshaller.unmarshal(file);

		} catch (Exception ex) {
			throw ex;
		}

		if (links != null) {

			for (Link link : links.getLinks()) {
				String requirement = link.getSource_artifact_id();
				String artifactID = link.getTarget_artifact_id();

				String artifact = mappedArtifacts.getMapIdArtifact().get(
						artifactID);

				TraceLink traceLink = new TraceLink(requirement, "Dependency",
						mappedArtifacts.getType(), artifact);

				mappedArtifacts.getTraceLinksList().add(traceLink);
			}

			System.out.println();
			System.out.println("- File: " + file.getName());
			System.out.println("	Tracelinks: " + links.getLinks().size());

		}

		return mappedArtifacts.getTraceLinksList();
	}

}
