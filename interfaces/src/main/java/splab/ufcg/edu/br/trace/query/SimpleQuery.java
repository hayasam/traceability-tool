package splab.ufcg.edu.br.trace.query;

public class SimpleQuery extends QueryExpression {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3389176229093913593L;

	protected QueryOperatorEnum operator;
	
	protected QueryParameterTypeEnum parameter;
	
	protected String value;

	public SimpleQuery(String value) {
		this.value = value;
	}

	public SimpleQuery(QueryOperatorEnum all) {
		this.operator = all;
	}

	public SimpleQuery(QueryOperatorEnum not, String value) {
		this.operator = not;
		this.value = value;
	}

	public QueryOperatorEnum getOperator() {
		return operator;
	}

	public void setOperator(QueryOperatorEnum operator) {
		this.operator = operator;
	}

	public QueryParameterTypeEnum getParameter() {
		return parameter;
	}

	public void setParameter(QueryParameterTypeEnum parameter) {
		this.parameter = parameter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (operator != null) {
			str.append(operator.toString());
			str.append(" ");
		}
		if (value != null) {
			str.append(value);
			str.append(" ");
		}
		return str.toString();
		
	}
	
	
	
}
