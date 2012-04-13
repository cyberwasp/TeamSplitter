package ru.cyberwasp.teamsplitter;

public class Player {

	private final double metric;

	public Player(){
		this(0);
	}
	
	public Player(double metric) {
		this.metric = metric;
	}

	public double getMetric() {
		return metric;
	}

}
