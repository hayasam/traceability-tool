package splab.ufcg.edu.br.trace.exceptions;

public class QueryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5170594303233223133L;

	private String query;
	
	public QueryException(String message) {
		super(message);
		
	}

	public QueryException(String message, Exception ex) {
		super(message, ex);
	}
	


	public QueryException(String query, String message, Exception ex) {
		super(message, ex);
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	
	
}
