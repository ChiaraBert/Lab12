package it.polito.tdp.rivers.model;

import java.time.Duration;

import java.time.LocalDateTime;

public class Event implements Comparable<Event> {
	
	public enum EventType{FLUSSO_ENTRANTE,FLUSSO_USCENTE,TRACIMAZIONE};

	private LocalDateTime date;
	private Flow flusso;
	private EventType type;
	
	
	public Event(LocalDateTime date,Flow flusso, EventType type) {
		super();
		this.date=date;
		this.flusso = flusso;
		this.type = type;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Flow getFlusso() {
		return flusso;
	}

	public void setFlusso(Flow flusso) {
		this.flusso = flusso;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}


	@Override
	public int compareTo(Event ar) {
		
		if(this.date.isBefore(ar.getDate())){
			return -1;
		}
		
		if(this.date.isAfter(ar.getDate())){
			return 1;
		}
		
		else return 0;
	}

	@Override
	public String toString() {
		return "Event [date=" + date + ", flusso=" + flusso + ", type=" + type + "]";
	}
	
	

}
