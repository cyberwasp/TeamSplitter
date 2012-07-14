package ru.cyberwasp.teamsplitter;

import java.text.DecimalFormat;

public class Player {

    private static int gen = 0;
    private double metric;
    private String name;
    private long id;
    private Team team;

    public Player() {
        this(0);
    }

    public Player(double metric) {
        this(gen + 1, genName(), metric);
    }

    private static String genName() {
        gen += 1;
        return "Name" + gen;
    }

    public Player(long id, String name, double metric) {
        this.id = id;
        this.metric = metric;
        this.name = name;
    }

    public Player(Player player) {
        this.id = player.id;
        this.metric = player.metric;
        this.name = player.name;
    }

    public Player(String name, double metric) {
        this(gen + 1, name, metric);
    }

    public double getMetric() {
        return metric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setMetric(double metric) {
        this.metric = metric;
    }

    public long getId() {
        return id;
    }

    public String getInfo() {
        DecimalFormat fmt = new DecimalFormat("#.##");
        String metric = fmt.format(getMetric());
        return " " + getName() + " (" + metric + ")";
    }

    public void setTeam(Team team) {
        if (this.team != null)
            this.team.remove(this);
        if (team != null)
            team.add(this);
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

}
