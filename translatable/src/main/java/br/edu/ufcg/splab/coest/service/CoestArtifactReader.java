package br.edu.ufcg.splab.coest.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import br.edu.ufcg.splab.coest.data.Artifact;
import br.edu.ufcg.splab.coest.data.ArtifactCollection;
import br.edu.ufcg.splab.coest.data.MappedArtifacts;

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
