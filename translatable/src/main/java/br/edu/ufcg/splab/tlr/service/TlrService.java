package br.edu.ufcg.splab.tlr.service;

import java.io.File;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import br.edu.ufcg.splab.util.TlrProperties;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.interfaces.Translatable;

public class TlrService implements Translatable {

	@Inject
	private TlrProperties properties;

	public TraceLinkList read() {
		return this.read(null);
	}

	@Override
	public TraceLinkList read(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(File file, TraceLinkList tracelinks) {
		properties.loadProperties();
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

	public void write(TraceLinkList traceLinks) {
		File file = new File(properties.getWriterDirectory());
		this.write(file, traceLinks);
	}
}
