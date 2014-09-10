package splab.ufcg.edu.br.trace.enumeration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TraceLinkElementEnum {

	REQUIREMENT {
		@Override
		public String toString() {
			return "Requirement";
		}
	},

	SEMANTIC {
		@Override
		public String toString() {
			return "Semantic";
		}
	},

	ARTIFACT {
		@Override
		public String toString() {
			return "Artifact";
		}
	},

	ARTIFACT_TYPE {
		@Override
		public String toString() {
			return "Artifact Type";
		}
	};

	/**
	 * Get all elements of TraceLinkElementEnum as a list of string
	 * 
	 * @return List<String> - trace link elements as list
	 */
	public List<String> getTraceLinkElementsList() {
		String[] result = { 
				REQUIREMENT.toString(), 
				SEMANTIC.toString(),
				ARTIFACT_TYPE.toString(),
				ARTIFACT.toString() };

		return Arrays.asList(result);

	}
	
	
	public static TraceLinkElementEnum valueOfElement(final String name) {
        for(TraceLinkElementEnum element : values()){
        	if(element.toString().equalsIgnoreCase(name)){
        		return element;        		
        	}
        	
        }
        
        return null;
    }

}
