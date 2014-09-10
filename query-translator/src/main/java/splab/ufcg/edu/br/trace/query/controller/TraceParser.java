package splab.ufcg.edu.br.trace.query.controller;

import java.util.LinkedList;
import java.util.List;

import splab.ufcg.edu.br.trace.entities.TraceQuery;

public class TraceParser {

	
	private static TraceParser instance;
	
	private List<TraceQuery> parsedQueries;

    public List<TraceQuery> getParsedQueries(){
        return this.parsedQueries;
    }

    public void addTraceQuery(TraceQuery query) {
        if (this.parsedQueries == null) {
            this.parsedQueries = new LinkedList<TraceQuery>();

        } 
        this.parsedQueries.add(query);
    }
    
    
    private TraceParser(){
    	this.parsedQueries = new LinkedList<TraceQuery>();
    }
    
    public static TraceParser getInstance(){
    	if (instance == null){
    		instance = new TraceParser();
    	}
    	return instance;
    }
}
