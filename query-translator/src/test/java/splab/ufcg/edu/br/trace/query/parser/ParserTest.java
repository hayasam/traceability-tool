package splab.ufcg.edu.br.trace.query.parser;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.trace.entities.TraceQuery;
import splab.ufcg.edu.br.trace.query.controller.TraceParser;


@RunWith(JUnit4.class)
public class ParserTest {

	private parser parser;

	private static final String path = "/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/query-translator/src/test/resources/input.txt";

	@Before
	public void setUp() throws FileNotFoundException {

		Lexer scanner = new Lexer(new java.io.FileReader(path));

		parser = new parser(scanner);
	}

	@Test
	public void testParser() throws Exception {
		parser.parse();

		TraceParser parsedQueries = TraceParser.getInstance();

		assertNotNull(parsedQueries);

		assertNotNull(parsedQueries.getParsedQueries());

		assertFalse(parsedQueries.getParsedQueries().isEmpty());
	}

	@Test
	public void testParsedQueries() throws Exception {
		parser.parse();

		TraceParser parsedQueries = TraceParser.getInstance();

		assertNotNull(parsedQueries);

		assertNotNull(parsedQueries.getParsedQueries());

		assertFalse(parsedQueries.getParsedQueries().isEmpty());

		List<TraceQuery> queries = parsedQueries.getParsedQueries();

		for (TraceQuery traceQuery : queries) {

			System.out.println(traceQuery.toString());
			System.out.println("------------------------------------------------------------------------");
		}


	}

}
