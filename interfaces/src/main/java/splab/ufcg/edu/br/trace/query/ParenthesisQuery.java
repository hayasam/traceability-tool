package splab.ufcg.edu.br.trace.query;

public class ParenthesisQuery extends QueryExpression {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8964283006868338475L;
	protected QueryExpression innerExpression;

	public ParenthesisQuery(QueryExpression expr) {
		this.innerExpression = expr;
	}

	public QueryExpression getInnerExpression() {
		return innerExpression;
	}

	public void setInnerExpression(QueryExpression innerExpression) {
		this.innerExpression = innerExpression;
	}
	
	@Override
	public String toString(){		
		return String.format("(%s)", innerExpression == null ? " " : innerExpression.toString());
	}
	

}
