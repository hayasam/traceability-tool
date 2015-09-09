package br.edu.ufcg.splab.testlink.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import splab.ufcg.edu.br.trace.entities.SemanticMeaning;
import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;

public class TestLinkDAO {

	@Inject
	private RemoteDataBaseConnector remoteDataBaseConnector;

	private TraceLinkList tracelinks;

	private static final String GET_TRACES_FROM_EPOL_TEST_LINK = new StringBuilder()
			.append("select kw.keyword as req, 'Test_Case' as source, tc.name as name ")
			.append("from nodes_hierarchy as tc,  ")
			.append("testcase_keywords as kwtc,  ")
			.append("keywords as kw ")
			.append("where kwtc.keyword_id = kw.id and ")
			.append("kwtc.testcase_id = tc.id and  ")
			.append("tc.parent_id in (  ")
			.append("select uc.id from nodes_hierarchy as uc where uc.parent_id in ( ")
			.append("select re.id from nodes_hierarchy as re where re.parent_id = ")
			.append("(select proj.id from nodes_hierarchy as proj where proj.node_type_id = 1 and proj.name = 'arthur-profes-marcado'))); ")
			.toString();

	public TraceLinkList getTraceLinks() {
		tracelinks = new TraceLinkList();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		this.remoteDataBaseConnector = new RemoteDataBaseConnector();

		try {

			connection = this.remoteDataBaseConnector.getConnection();

			if (connection != null) {
				statement = connection.createStatement();

				resultSet = statement
						.executeQuery(GET_TRACES_FROM_EPOL_TEST_LINK);

				this.readResultSet(resultSet);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			this.closeConnections(connection, statement, resultSet);
		}
		return tracelinks;
	}

	private void readResultSet(ResultSet resultSet) throws SQLException{

		List<TraceLink> tracelinklist = new ArrayList<TraceLink>();

		while (resultSet.next()) {
			TraceLink tracelink = new TraceLink(resultSet.getString("req"),
					this.getSemanticMeaning(), resultSet.getString("source"),
					resultSet.getString("name"));

			if (tracelink != null) {
				tracelinklist.add(tracelink);

			}
		}

		this.tracelinks.setTraceLinks(tracelinklist);
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

	/**
	 * get the semantic meaning from extracted artifacts
	 * 
	 * @return String - semantic meaning
	 */
	private String getSemanticMeaning() {

		return SemanticMeaning.OVERLAPPING.toString();
	}

}
