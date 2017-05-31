package it.polito.tdp.rivers.model;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.rivers.model.Event.EventType;



public class Simulator {
	
	//parametri di simulazione
	private double Q;
	private double flussoMinimoUscita;
	private double flussoMassimoUscita;
	
	
	//Stato del mondo
	private double C;
	
	//Variabili di interesse 
	private double k;
	private int numeroGiorni;
	private double Cmedia;
	private double flussoMedio;
	
	
	//lista degli eventi
	private PriorityQueue<Event> queue;
	private LinkedList<Flow> flussi = new LinkedList<Flow>();
	
	public Simulator(double k, double flussoMedio) {
		super();
		this.k = k;
		this.flussoMedio = flussoMedio;
		queue= new PriorityQueue<Event>();
		
		
		this.Q=k*flussoMedio*60*60*24*30;
		this.C=Q/2;
		this.flussoMinimoUscita=0.8*flussoMedio*60*60*24;
		this.flussoMassimoUscita=10*flussoMinimoUscita;
		
		
	}
	
	public void aggiungiEvento(){
		for(Flow f: flussi){
			Event e = new Event(f.getDay(), f, EventType.FLUSSO_ENTRANTE);
			queue.add(e);
			}
	}
	
	public void run(){
		
		numeroGiorni=0;
		Cmedia=0;
		
		
		while(!queue.isEmpty()){
			Event e = queue.poll();
			
						
			switch(e.getType()){
			case FLUSSO_ENTRANTE:
				
				if(C+e.getFlusso().getFlow()*60*60*24>Q){
					C=C+e.getFlusso().getFlow()*60*60*24;
					//evento tracinamento
					Event d = new Event(e.getDate(),e.getFlusso(),EventType.TRACIMAZIONE);
					queue.add(d);
				}
				else {
					//evento flusso uscita
					C=C+e.getFlusso().getFlow()*60*60*24;
										
					if(C>=flussoMinimoUscita){
					Event d2 = new Event(e.getDate(),e.getFlusso(),EventType.FLUSSO_USCENTE);
					queue.add(d2);
				}
					else 
						numeroGiorni++;
						Cmedia+=C;}
				break;
				
			case FLUSSO_USCENTE:
				
												
				if (Math.random() > 0.95){
				if(C>=flussoMassimoUscita){
				C=C-flussoMassimoUscita;
				Cmedia+=C;}
				else numeroGiorni++;
				
				}
			
				else{
				C=C-flussoMinimoUscita;
				Cmedia+=C;
				}
				break;
			
			case TRACIMAZIONE:
				C=C-Q;
				Cmedia+=C;
				
				break;
			}
			
		}
		
	}
	
	
	public int getNumeroGiorni() {
		return numeroGiorni;
	}

	public double getCmedia() {
		return Cmedia/flussi.size();
	}
	
	public void setFlusso(LinkedList<Flow> flussi){
		this.flussi=flussi;
	}

	
		
	

	
}
