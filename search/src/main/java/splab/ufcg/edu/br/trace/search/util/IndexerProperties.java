package splab.ufcg.edu.br.trace.search.util;

import java.util.Properties;

import splab.ufcg.edu.br.trace.entities.AbstractSystemProperties;
import splab.ufcg.edu.br.trace.exceptions.PropertiesException;



public class IndexerProperties extends AbstractSystemProperties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1043925776521896945L;
	private final String MODE_PROPERTIE = "indexer.mode";
	private final String DIRECTORY_PROPERTIE = "indexer.directory";

	private ExecutionMode mode;

	public ExecutionMode getMode() {
		return mode;
	}

	public void setMode(ExecutionMode mode) {
		this.mode = mode;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	private String directory;

	/**
	 * Default ConectionProperties constructor
	 * 
	 * @param String
	 *            path to properties file
	 * @throws PropertiesException
	 *             if cannot read properties files
	 */
	public IndexerProperties(String path) throws PropertiesException {
		super(path);
	}
//
//	public IndexerProperties(Properties properties) {
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public void setPropertyByKey(String key, String value, String line)
			throws PropertiesException {

		switch (key) {
		case MODE_PROPERTIE:
			try {

				this.setMode(Enum.valueOf(ExecutionMode.class,
						value.toUpperCase()));
				break;
			} catch (Exception e) {
				StringBuilder message = new StringBuilder();
				message.append("Propertie cannot be mapped into a execution mode .line: ");
				message.append(line);
				throw new PropertiesException(message.toString());
			}
		case DIRECTORY_PROPERTIE:
			this.setDirectory(value);
			break;

		default:
			StringBuilder message = new StringBuilder();
			message.append("Cannot read propertie in line: ");
			message.append(line);
			throw new PropertiesException(message.toString());
		}

	}

}
