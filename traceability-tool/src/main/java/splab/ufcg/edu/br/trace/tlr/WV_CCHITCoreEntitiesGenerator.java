package splab.ufcg.edu.br.trace.tlr;

import java.util.List;
import java.util.Map;
import java.util.Set;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.query.QueryParameterTypeEnum;

public class WV_CCHITCoreEntitiesGenerator implements TLRGenerator {

	private static final String QUERY_NAME_TEMPLATE = "%s_query";

	private final String TLR_REQUIREMENT_TEMPLATE = "requirement %s = {  \'%s\'   };";
	private final String TLR_TYPE_TEMPLATE = "type %s;";
	private final String TLR_SEMANTIC_TEMPLATE = "semantic %s;";

	private final String TLR_QUERY_TEMPLATE = "query %s (%s %s) { "
			+ System.lineSeparator() + "    result %s; "
			+ System.lineSeparator() + "}";

	private Map<String, String> mappedRequirements;

	private Set<String> artifactTypes;

	private Set<String> semantics;

	public WV_CCHITCoreEntitiesGenerator(
			Map<String, String> mappedRequirements, Set<String> artifactTypes,
			Set<String> semantics) {
		this.setMappedRequirements(mappedRequirements);
		this.setArtifactTypes(artifactTypes);
		this.setSemantics(semantics);
	}

	@Override
	public String generateTLR() {

		StringBuilder str = new StringBuilder();

		str.append(this.getRequirements());
		str.append(TLR_LINE_BREAK);

		str.append(this.getArtifactTypes());
		str.append(TLR_LINE_BREAK);

		str.append(this.getSemantics());
		str.append(TLR_LINE_BREAK);

		str.append(this.getDefaultQueries());
		str.append(TLR_LINE_BREAK);

		return str.toString();
	}

	private String getDefaultQueries() {

		StringBuilder str = new StringBuilder();

		List<String> queryTypes = QueryParameterTypeEnum.getQueryParameters();

		for (String type : queryTypes) {
			str.append(getQueryFromType(type));
			str.append(TLR_LINE_BREAK);
		}
		str.append(TLR_LINE_BREAK);
		return str.toString();
	}

	private String getQueryFromType(String type) {

		String queryName = String.format(QUERY_NAME_TEMPLATE, type);
		String parameterType = type;
		String parameterValue = type.substring(0, 1);

		return String.format(TLR_QUERY_TEMPLATE, queryName, parameterType,
				parameterValue, parameterValue);
	}

	private String getSemantics() {
		StringBuilder str = new StringBuilder();

		for (String semantic : this.semantics) {
			str.append(getSemanticDeclaration(semantic));
			str.append(TLR_LINE_BREAK);
		}
		str.append(TLR_LINE_BREAK);
		return str.toString();
	}

	private String getArtifactTypes() {

		StringBuilder str = new StringBuilder();

		for (String type : this.artifactTypes) {
			str.append(getTypeDeclaration(type));
			str.append(TLR_LINE_BREAK);
		}
		str.append(TLR_LINE_BREAK);

		return str.toString();
	}

	private String getRequirements() {

		StringBuilder str = new StringBuilder();

		for (String requirement : this.mappedRequirements.keySet()) {
			str.append(getRequirementDeclaration(requirement,
					mappedRequirements.get(requirement)));
			str.append(TLR_LINE_BREAK);
		}

		return str.toString();
	}

	private String getSemanticDeclaration(String semantic) {

		return String.format(TLR_SEMANTIC_TEMPLATE, semantic);
	}

	private String getRequirementDeclaration(String requirement, String content) {
		return String.format(TLR_REQUIREMENT_TEMPLATE, requirement, content);
	}

	private String getTypeDeclaration(String type) {
		return String.format(TLR_TYPE_TEMPLATE, type);
	}

	public Map<String, String> getMappedRequirements() {
		return mappedRequirements;
	}

	public void setMappedRequirements(Map<String, String> mappedRequirements) {
		this.mappedRequirements = mappedRequirements;
	}

	public void setArtifactTypes(Set<String> artifactTypes2) {
		this.artifactTypes = artifactTypes2;
	}

	public void setSemantics(Set<String> semantics2) {
		this.semantics = semantics2;
	}

}
