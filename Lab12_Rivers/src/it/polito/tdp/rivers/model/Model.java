package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	List<River> fiumi;
	
	
	public Model(){
		fiumi = new ArrayList<River>();
	}

	public List<River> getFiumi() {
		RiversDAO rv = new RiversDAO();
		fiumi=rv.getAllRivers();
		return fiumi;
	}
	
	public LinkedList<Flow> getDati(River r){
		RiversDAO rv = new RiversDAO();
		LinkedList<Flow> flussi = new LinkedList<>();
		flussi=rv.getFlows(r);
		return flussi;
	}
	
	
	

}
