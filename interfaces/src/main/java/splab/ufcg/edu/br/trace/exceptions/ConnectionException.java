package splab.ufcg.edu.br.trace.exceptions;


public class ConnectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1446646390850658833L;

	public ConnectionException(String message) {
		
		super(message);
	}

	public ConnectionException(String message, Exception ex) {

		super(message, ex);
	}

}
