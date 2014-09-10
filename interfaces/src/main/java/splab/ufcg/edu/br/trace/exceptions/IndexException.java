package splab.ufcg.edu.br.trace.exceptions;


public class IndexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3242362233019375049L;

	public IndexException(String message) {
		super(message);
		
	}

	public IndexException(String message, Exception ex) {
		super(message, ex);
	}

}
