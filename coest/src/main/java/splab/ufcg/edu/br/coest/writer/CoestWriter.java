package splab.ufcg.edu.br.coest.writer;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import splab.ufcg.edu.br.coest.entities.AnswerSet;
import splab.ufcg.edu.br.coest.entities.ArtifactCollection;
import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.interfaces.Translatable;

public class CoestWriter implements Translatable {
	
	@Override
	public TraceLinkList read(File file) throws Exception {

		AnswerSet result = null;

		try {

			JAXBContext jaxbContext = JAXBContext
					.newInstance(AnswerSet.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			result = (AnswerSet) jaxbUnmarshaller.unmarshal(file);

		} catch (Exception ex) {
//			ex.printStackTrace();
			throw ex;
		}
		
		if (result != null){
			// TODO processar resultado
		}

		return null;
	}

	@Override
	public void write(File file, TraceLinkList tracelinks) throws Exception {

		try {

			JAXBContext jaxbContext = JAXBContext
					.newInstance(TraceLinkList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(tracelinks, file);
			// jaxbMarshaller.marshal(tracelinks, System.out);

		} catch (Exception ex) {
//			ex.printStackTrace();
			throw ex;
		}

	}

}
