package splab.ufcg.edu.br.trace.entities;

import splab.ufcg.edu.br.trace.enumeration.*;

/**
 * Enumerator that represents Apache SolrJ manipulatable field
 * 
 * @author Arthur
 *
 */
public enum ApacheSolrJFieldEnum {

	ID {
		@Override
		public String toString() {
			return "id";
		}
	},

	REQUIREMENT {
		@Override
		public String toString() {
			return "requirement";
		}
	},

	SEMANTIC {
		@Override
		public String toString() {
			return "semantic";
		}
	},

	ARTIFACT {
		@Override
		public String toString() {
			return "artifact";
		}
	},

	ARTIFACT_TYPE {
		@Override
		public String toString() {
			return "artifact_type";
		}
	};

	
	/**
	 * Convert a trace link element into an Apache SolrJ element
	 * 
	 * @param tracelinkElement
	 * @return
	 */
	public static ApacheSolrJFieldEnum convertToSolrJField(
			TraceLinkElementEnum tracelinkElement) {

		switch (tracelinkElement) {
		case REQUIREMENT:
			return ApacheSolrJFieldEnum.REQUIREMENT;			

		case SEMANTIC:
			return ApacheSolrJFieldEnum.SEMANTIC;

		case ARTIFACT_TYPE:
			return ApacheSolrJFieldEnum.ARTIFACT_TYPE;

		case ARTIFACT:
			return ApacheSolrJFieldEnum.ARTIFACT;

		default:
			break;
		}

		return null;
	}

}
