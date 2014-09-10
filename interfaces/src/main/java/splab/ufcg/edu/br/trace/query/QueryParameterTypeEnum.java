package splab.ufcg.edu.br.trace.query;

import java.util.Arrays;
import java.util.List;

public enum QueryParameterTypeEnum {

	DEFAULT {
		@Override
		public String toString() {
			return "default";
		}
	},
	REQUIREMENT  {
		@Override
		public String toString() {
			return "requirement";
		}
	},
	SEMANTIC  {
		@Override
		public String toString() {
			return "semantic";
		}
	},
	ARTIFACT  {
		@Override
		public String toString() {
			return "artifact";
		}
	},
	ARTIFACT_TYPE  {
		@Override
		public String toString() {
			return "artifact_type";
		}
	},
	DOMAIN  {
		@Override
		public String toString() {
			return "domain";
		}
	};
	
	/**
	 * Get all parameter elements as a list of string
	 * 
	 * @return List<String> - trace link elements as list
	 */
	public static List<String> getQueryParameters() {
		String[] result = { 
				REQUIREMENT.toString(), 
				SEMANTIC.toString(),
				"type",
				ARTIFACT.toString()
				//DOMAIN.toString()
				};

		return Arrays.asList(result);

	}
	
}
