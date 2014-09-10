package splab.ufcg.edu.br.trace.exceptions;

public class PropertiesException extends Exception {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 358885636627229258L;

	public PropertiesException(String message, Exception ex) {
		
		super(message, ex);
	}

	public PropertiesException(String message) {
		super (message);
	}
}
