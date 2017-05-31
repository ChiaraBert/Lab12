package it.polito.tdp.rivers.model;

import java.time.LocalDateTime;

public class Flow {

	private int id;
	private LocalDateTime day;
	private double flow;
	private River river;

	public Flow(int id,LocalDateTime day, double flow, River river) {
		this.id=id;
		this.day = day;
		this.flow = flow;
		this.river = river;
	}

	public LocalDateTime getDay() {
		return day;
	}

	public void setDay(LocalDateTime day) {
		this.day = day;
	}

	public double getFlow() {
		return flow;
	}

	public void setFlow(double flow) {
		this.flow = flow;
	}

	@Override
	public String toString() {
		return "Flow [day=" + day + ", flow=" + flow + ", river=" + river + "]";
	}

}
