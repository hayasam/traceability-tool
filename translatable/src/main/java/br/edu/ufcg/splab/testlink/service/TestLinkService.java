package br.edu.ufcg.splab.testlink.service;

import java.io.File;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.interfaces.Translatable;
import br.edu.ufcg.splab.util.TestLinkProperties;

public class TestLinkService implements Translatable {

	@Inject
	private TestLinkProperties properties;

	@Override
	public TraceLinkList read(File file) {
		TraceLinkList result = null;
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(TraceLinkList.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			result = (TraceLinkList) jaxbUnmarshaller.unmarshal(file);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

		return result;
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

	public TraceLinkList read() {
		throw new RuntimeException("Not implemented yet");
	}

	public void write(TraceLinkList traceLinks) throws Exception {
		File file = new File(properties.getWriterDirectory());
		this.write(file, traceLinks);
	}
}
