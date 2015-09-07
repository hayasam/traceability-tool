package splab.ufcg.edu.br.trace.query;

public class CompositeQuery extends QueryExpression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2129101234934799492L;

	protected QueryExpression leftExpression;

	protected QueryExpression rightExpression;

	protected QueryOperatorEnum operator;

	public CompositeQuery(QueryExpression left, QueryExpression right,
			QueryOperatorEnum op) {
		this.leftExpression = left;
		this.rightExpression = right;
		this.operator = op;
	}

	public QueryExpression getLeftExpression() {
		return leftExpression;
	}

	public void setLeftExpression(QueryExpression leftExpression) {
		this.leftExpression = leftExpression;
	}

	public QueryExpression getRightExpression() {
		return rightExpression;
	}

	public void setRightExpression(QueryExpression rightExpression) {
		this.rightExpression = rightExpression;
	}

	public QueryOperatorEnum getOperator() {
		return operator;
	}

	public void setOperator(QueryOperatorEnum operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();		
		if (leftExpression != null) {
			str.append(leftExpression.toString());
			str.append(" ");
		}
		
		if (operator != null) {
			str.append(System.getProperty("line.separator").toString());
			str.append(operator.toString());
			str.append(" ");
		}
		
		if (rightExpression != null) {
			str.append(rightExpression.toString());	
			str.append(" ");
		}
		
		
		return str.toString();
	}

	
	
}
