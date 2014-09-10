package splab.ufcg.edu.br.trace.indexer;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SolrJIDTest {
	
	
	private SolrJID id;
	
	@Before
	public void setUp(){
		id = SolrJID.getInstance();
	}
	
	@Test
	public void testIDGeneration(){
		long lastId = this.id.getLastId();
		lastId++;
		
		assertTrue(lastId == this.id.getNextId());
		
		lastId++;
		assertTrue(lastId == this.id.getNextId());
		
		lastId++;
		assertTrue(lastId == this.id.getNextId());
		
		
	}

}
