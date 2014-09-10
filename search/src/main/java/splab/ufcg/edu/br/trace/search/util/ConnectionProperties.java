package splab.ufcg.edu.br.trace.search.util;


import splab.ufcg.edu.br.trace.entities.AbstractSystemProperties;
import splab.ufcg.edu.br.trace.exceptions.PropertiesException;

public class ConnectionProperties extends AbstractSystemProperties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1995818392767857782L;

	private final String URL_PROPERTIE = "apache.url";
	private final String CORE_PROPERTIE = "apache.core";

	private String url;

	private String core;

	/**
	 * Default ConectionProperties constructor
	 * 
	 * @param String
	 *            path to properties file
	 * @throws PropertiesException
	 *             if cannot read properties files
	 */
	public ConnectionProperties(String path) throws PropertiesException {
		super(path);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCore() {
		return core;
	}

	public void setCore(String core) {
		this.core = core;
	}

	@Override
	public void setPropertyByKey(String key, String value, String line)
			throws PropertiesException {

		switch (key) {
		case URL_PROPERTIE:
			this.setUrl(value);
			break;

		case CORE_PROPERTIE:
			this.setCore(value);
			break;

		default:
			StringBuilder message = new StringBuilder();
			message.append("Cannot read propertie in line: ");
			message.append(line);
			throw new PropertiesException(message.toString());
		}

	}
	
	public String getConnectionUrl(){
		StringBuilder urlConn = new StringBuilder();
		
		urlConn.append(url);
		urlConn.append(core);
		
		return urlConn.toString();
	}

}
