package ru.cyberwasp.teamsplitter;

public class Player {
	
	private static int gen = 0;
	private final double metric;
	private String name;

	public Player(){
		this(0);
	}
	
	public Player(double metric) {
		this(genName(), metric);
	}

	private static String genName() {
		gen += 1;
		return "Name" + gen;
	}

	public Player(String name, double metric) {
		this.metric = metric;
		this.name = name;
	}

	public double getMetric() {
		return metric;
	}
	
	public String getName(){
		return name;
	}

}
