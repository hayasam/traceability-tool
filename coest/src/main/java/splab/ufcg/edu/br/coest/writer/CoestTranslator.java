package splab.ufcg.edu.br.coest.writer;

import java.io.File;
import java.util.Properties;

import splab.ufcg.edu.br.coest.entities.MappedArtifacts;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.interfaces.Traceable;

public class CoestTranslator implements Traceable {


	private static final String SEMICOLUMN = ";" ;
	private final String REQUIREMENTS_FILE = "coest.requirements";
	private final String ARTIFACT_FILE = "coest.artifacts";
	private final String TRACE_FILE = "coest.traces";
	
	private CoestArtifactReader artifactReader;

	private CoestTraceLinksReader linksReader;
	private Properties properties;

	


	public CoestTranslator(Properties properties) {
		
		this.properties = properties;

		this.artifactReader = new CoestArtifactReader();
		this.linksReader = new CoestTraceLinksReader();
		
	}

	@Override
	public TraceLinkList getTraceLinks() {

		MappedArtifacts map = null;
		TraceLinkList traceLinks = new TraceLinkList();
		try {
			
			if (!this.isArray()){
				File artifactFile = new File(this.getArtifactsFile());
				map = artifactReader.read(artifactFile);
				
				File linksFile = new File(this.getTracesFile());
				traceLinks = linksReader.read(linksFile, map);				
			} else {
				int arraySize = this.getArraySize();
				String[] files = this.getArtifactFiles();
				String[] traces = this.getTraceFiles();
				
				for (int i = 0; i < arraySize; i++) {
					File artifactFile = new File(files[i]);
					map = artifactReader.read(artifactFile);
					
					File linksFile = new File(traces[i]);
					TraceLinkList traceLinksTemp = linksReader.read(linksFile, map);
					
					traceLinks.addAll(traceLinksTemp);
					
				}
				
			}

		} catch (Exception e) {			
			e.printStackTrace();
		}

		return traceLinks;
	}

	private String[] getTraceFiles() throws Exception {
		this.checkProperties();
		String files = properties.getProperty(TRACE_FILE);
		 String[] split = files.split(SEMICOLUMN);
		
		return split;
	}

	private String[] getArtifactFiles() throws Exception {
		this.checkProperties();
		String files = properties.getProperty(ARTIFACT_FILE);
		 String[] split = files.split(SEMICOLUMN);
		
		return split;
	}

	private int getArraySize() throws Exception {
		this.checkProperties();
		String files = properties.getProperty(ARTIFACT_FILE);
		 String[] split = files.split(SEMICOLUMN);
		
		return split.length;
	}

	private boolean isArray() throws Exception {
		this.checkProperties();
		String files = properties.getProperty(ARTIFACT_FILE);
		return files.contains(SEMICOLUMN);
	}

	private String getArtifactsFile() throws Exception {

		this.checkProperties();
		return properties.getProperty(ARTIFACT_FILE);
	}

	private String getTracesFile() throws Exception {
		this.checkProperties();

		return properties.getProperty(TRACE_FILE);
	}
	
	private String getRequirementsFile() throws Exception {
		this.checkProperties();

		return properties.getProperty(REQUIREMENTS_FILE);
	}
	
	private void checkProperties() throws Exception {

		if (this.properties == null) {
			throw new Exception("Properties file not set.");
		}

	}


}
