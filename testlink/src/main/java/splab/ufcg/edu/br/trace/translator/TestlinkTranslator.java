package splab.ufcg.edu.br.trace.translator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import splab.ufcg.edu.br.trace.entities.SemanticMeaning;
import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.exceptions.TraceLinkException;
import splab.ufcg.edu.br.trace.interfaces.Traceable;
import splab.ufcg.edu.br.trace.testlink.util.Connector;

/**
 * Testlink translator This class establish a connection with the testlink
 * database and retrieve marked up
 * 
 * @author Arthur
 */
public class TestlinkTranslator implements Traceable {

	private Connector connector;

	

	private TraceLinkList tracelinks;



	private Properties properties;

	public TestlinkTranslator() { 
		this.tracelinks = new TraceLinkList();
		
	}
	
	


	public TestlinkTranslator(Properties properties) {
		this();
		this.properties = properties;		
	}

	public TraceLinkList getTraceLinks() {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		this.connector = new Connector();
		this.connector.setProperties(properties);

		try {

			connection = this.connector.getConnection();

			if (connection != null) {
				statement = connection.createStatement();
				String query = this.getQuery();
				resultSet = statement.executeQuery(query);

				this.readResultSet(resultSet);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			this.closeConnections(connection, statement, resultSet);
		}
		
		return tracelinks;

	}

	private void closeConnections(Connection connection, Statement statement,
			ResultSet resultSet) {
		try {
			if (statement != null) {
				statement.close();
			}

			if (resultSet != null) {
				resultSet.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void readResultSet(ResultSet resultSet) throws SQLException, TraceLinkException {

		List<TraceLink> tracelinklist = new ArrayList<TraceLink>();
		
		while (resultSet.next()) {

			TraceLink tracelink = new TraceLink(
					resultSet.getString("req"), this.getSemanticMeaning(),
					resultSet.getString("source"), resultSet.getString("name"));

			if (tracelink != null){
				tracelinklist.add(tracelink);
			
			}
		}
		
		this.tracelinks.setTraceLinks(tracelinklist);
	}

	/**
	 * get the semantic meaning from extracted artifacts
	 * 
	 * @return String - semantic meaning
	 */
	private String getSemanticMeaning() {

		return SemanticMeaning.OVERLAPPING.toString();
	}

	private String getQuery() {
		StringBuilder query = new StringBuilder();

		query.append("select kw.keyword as req, 'Test_Case' as source, tc.name as name ");
		query.append("from nodes_hierarchy as tc,  ");
		query.append("testcase_keywords as kwtc,  ");
		query.append("keywords as kw ");
		query.append("where kwtc.keyword_id = kw.id and ");
		query.append("kwtc.testcase_id = tc.id and  ");
		query.append("tc.parent_id in (  ");
		query.append("select uc.id from nodes_hierarchy as uc where uc.parent_id in ( ");
		query.append("select re.id from nodes_hierarchy as re where re.parent_id = ");
		query.append("(select proj.id from nodes_hierarchy as proj where proj.node_type_id = 1 and proj.name = 'arthur-profes-marcado'))); ");

		return query.toString();
	}

}
