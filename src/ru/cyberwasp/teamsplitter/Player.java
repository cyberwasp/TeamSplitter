package ru.cyberwasp.teamsplitter;

import java.text.DecimalFormat;

public class Player {
	
	private static int gen = 0;
	private final double metric;
	private String name;
	private int id;

	public Player(){
		this(0);
	}
	
	public Player(double metric) {
		this(gen+1, genName(), metric);
	}

	private static String genName() {
		gen += 1;
		return "Name" + gen;
	}

	public Player(int id, String name, double metric) {
		this.id = id;
		this.metric = metric;
		this.name = name;
	}

	public double getMetric() {
		return metric;
	}
	
	public String getName(){
		return name;
	}

	public int getId() {
		return id;
	}
	
	public String getInfo(){
		DecimalFormat fmt = new DecimalFormat("#.##");
		String metric = fmt.format(getMetric());
		return " " + getName() + " (metric = " + metric + ")";
	}
	
	
}
