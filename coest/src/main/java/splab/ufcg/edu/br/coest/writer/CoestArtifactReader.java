package splab.ufcg.edu.br.coest.writer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import splab.ufcg.edu.br.coest.entities.AnswerSet;
import splab.ufcg.edu.br.coest.entities.Artifact;
import splab.ufcg.edu.br.coest.entities.ArtifactCollection;
import splab.ufcg.edu.br.coest.entities.MappedArtifacts;
import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.interfaces.Translatable;

public class CoestArtifactReader {
	
	
	public MappedArtifacts read(File file) throws Exception {

		MappedArtifacts result = new MappedArtifacts();
		Map<String, String> idArtifactMap = new HashMap<String, String>();
		
		ArtifactCollection artifacts = null;

		try {

			JAXBContext jaxbContext = JAXBContext
					.newInstance(ArtifactCollection.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			artifacts = (ArtifactCollection) jaxbUnmarshaller.unmarshal(file);

		} catch (Exception ex) {
//			ex.printStackTrace();
			throw ex;
		}
		
		if (artifacts != null){

			String description = artifacts.getCollection_info().getDescription();			
			String type = description.replaceAll(" ", "_");
			
			for (Artifact artifact : artifacts.getArtifacts()) {
				idArtifactMap.put(artifact.getId(), artifact.getContent());
			}
			
			result.setType(type);
			result.setMapIdArtifact(idArtifactMap);
			
			System.out.println();
			System.out.println("- File: " +file.getName());
			System.out.println("	Artifacts: " +artifacts.getArtifacts().size());
			
		
		}

		return result;
	}


}
