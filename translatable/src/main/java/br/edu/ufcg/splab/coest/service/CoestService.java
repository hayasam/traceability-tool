package br.edu.ufcg.splab.coest.service;

import java.io.File;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.interfaces.Translatable;
import br.edu.ufcg.splab.coest.data.MappedArtifacts;
import br.edu.ufcg.splab.util.CoestProperties;

public class CoestService implements Translatable {

	private CoestArtifactReader artifactReader;

	private CoestTraceLinksReader linksReader;

	@Inject
	private CoestProperties properties;

	public TraceLinkList read() throws Exception {
		properties.loadProperties();
		return this.read(null);
	}

	@Override
	public TraceLinkList read(File fileProperty)  {
		this.artifactReader = new CoestArtifactReader();
		this.linksReader = new CoestTraceLinksReader();

		MappedArtifacts map = null;
		TraceLinkList traceLinks = new TraceLinkList();

		try {
			if (!properties.isArray()) {
				File artifactFile = new File(properties.getArtifactsFile());
				map = artifactReader.read(artifactFile);

				File linksFile = new File(properties.getTracesFile());
				traceLinks = linksReader.read(linksFile, map);
			} else {
				int arraySize = properties.getArraySize();
				String[] files = properties.getArtifactFiles();
				String[] traces = properties.getTraceFiles();

				for (int i = 0; i < arraySize; i++) {
					File artifactFile = new File(files[i]);
					map = artifactReader.read(artifactFile);
					File linksFile = new File(traces[i]);
					TraceLinkList traceLinksTemp = linksReader.read(linksFile,
							map);
					traceLinks.addAll(traceLinksTemp);
				}
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return traceLinks;
	}

	@Override
	public void write(File file, TraceLinkList tracelinks)  {
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(TraceLinkList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(tracelinks, file);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public void write(TraceLinkList traceLinks) throws Exception {
		File file = new File(properties.getWriterDirectory());
		this.write(file, traceLinks);
	}
}
