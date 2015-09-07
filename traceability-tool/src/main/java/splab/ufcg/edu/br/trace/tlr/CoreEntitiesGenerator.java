package splab.ufcg.edu.br.trace.tlr;

import java.util.List;
import java.util.Set;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.query.QueryParameterTypeEnum;

public class CoreEntitiesGenerator implements TLRGenerator {

	private static final String QUERY_NAME_TEMPLATE = "%s_query";

	private final String TLR_REQUIREMENT_TEMPLATE = "requirement %s;";
	private final String TLR_TYPE_TEMPLATE = "type %s;";
	private final String TLR_SEMANTIC_TEMPLATE = "semantic %s;";

	private final String TLR_QUERY_TEMPLATE = "query %s (%s %s) { "
			+ System.lineSeparator() + "    result %s; "
			+ System.lineSeparator() + "}";

	private TraceLinkList extractedTraceLinkList;

	public CoreEntitiesGenerator(TraceLinkList extractedTraceLinkList) {
		this.extractedTraceLinkList = extractedTraceLinkList;
	}

	@Override
	public String generateTLR() {

		StringBuilder str = new StringBuilder();

		str.append(this.getRequirements(extractedTraceLinkList
				.extractRequirements()));
		str.append(TLR_LINE_BREAK);

		str.append(this.getArtifactTypes(extractedTraceLinkList
				.extractArtifactTypes()));
		str.append(TLR_LINE_BREAK);

		str.append(this.getSemantics(extractedTraceLinkList.extractSemantics()));
		str.append(TLR_LINE_BREAK);

		// FIXME
//		str.append(this.getDefaultQueries());
//		str.append(TLR_LINE_BREAK);

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

	private String getSemantics(Set<String> semantics) {
		StringBuilder str = new StringBuilder();

		for (String semantic : semantics) {
			str.append(getSemanticDeclaration(semantic));
			str.append(TLR_LINE_BREAK);
		}
		str.append(TLR_LINE_BREAK);
		return str.toString();
	}

	private String getArtifactTypes(Set<String> artifactTypes) {

		StringBuilder str = new StringBuilder();

		for (String type : artifactTypes) {
			str.append(getTypeDeclaration(type));
			str.append(TLR_LINE_BREAK);
		}
		str.append(TLR_LINE_BREAK);

		return str.toString();
	}

	private String getRequirements(Set<String> requirements) {

		StringBuilder str = new StringBuilder();

		for (String requirement : requirements) {
			str.append(getRequirementDeclaration(requirement));
			str.append(TLR_LINE_BREAK);
		}

		return str.toString();
	}

	private String getSemanticDeclaration(String semantic) {

		return String.format(TLR_SEMANTIC_TEMPLATE, semantic);
	}

	private String getRequirementDeclaration(String requirement) {
		return String.format(TLR_REQUIREMENT_TEMPLATE, requirement);
	}

	private String getTypeDeclaration(String type) {
		return String.format(TLR_TYPE_TEMPLATE, type);
	}

}
