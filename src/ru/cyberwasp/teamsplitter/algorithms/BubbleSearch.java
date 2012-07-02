package ru.cyberwasp.teamsplitter.algorithms;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.Team;

import java.util.HashSet;
import java.util.Set;

public class BubbleSearch implements SplitAlgorithm {

    private Player[] players = null;
    private Team[] teams = null;
    private double teamAvg = 0;
    private Set<String> history = null;

    protected void genFirstTeams(int teamCount) {
        teams = new Team[teamCount];
        for (int i = 0; i < teamCount; i++) {
            teams[i] = new Team(i);
        }
        for (int i = 0; i < players.length; i++) {
            players[i].setTeam(teams[i % teamCount]);
        }
    }

    @Override
    public Team[] split(Player[] players, int teamCount) {
        this.players = players;
        this.history = new HashSet<String>();
        genFirstTeams(teamCount);
        calcTeamAvg();
        runSplit();
        return this.teams;
    }

    private void calcTeamAvg() {
        double total = 0;
        for (int i = 0; i < players.length; i++) {
            total += players[i].getMetric();
        }
        teamAvg = total / teams.length;
    }

    private void runSplit() {
        boolean needStop = false;
        while (!needStop) {
            needStop = true;
            for (int i = 0; i < teams.length; i++) {
                for (int j = 0; j < teams.length; j++) {
                    Team t1 = teams[i];
                    Team t2 = teams[j];
                    if (t1.getID() > t2.getID()) {
                        if (exchangeTwoPlayers(t1, t2)) {
                            needStop = false;
                            break;
                        }
                    }
                }
                if (!needStop)
                    break;
            }
        }
    }

    private boolean exchangeTwoPlayers(Team t1, Team t2) {
        double diff = Math.abs(t1.getMetric() - teamAvg);
        diff += Math.abs(t2.getMetric() - teamAvg);
        for (Player p1 : t1.getPlayerList()) {
            double new_m1 = t1.getMetric() - p1.getMetric();
            for (Player p2 : t2.getPlayerList()) {
                double new_m2 = t2.getMetric() - p2.getMetric();
                double new_diff = Math.abs((new_m1 + p2.getMetric()) - teamAvg);
                new_diff += Math.abs((new_m2 + p1.getMetric()) - teamAvg);
                if (new_diff < diff) {
                    p1.setTeam(t2);
                    p2.setTeam(t1);
                    history.add(genState(null, null));
                    return true;
                }
            }
        }

        for (Player p1 : t1.getPlayerList()) {
            double new_m1 = t1.getMetric() - p1.getMetric();
            for (Player p2 : t2.getPlayerList()) {
                double new_m2 = t2.getMetric() - p2.getMetric();
                double new_diff = Math.abs((new_m1 + p2.getMetric()) - teamAvg);
                new_diff += Math.abs((new_m2 + p1.getMetric()) - teamAvg);
                if ((new_diff == diff) && (p1.getMetric() != p2.getMetric())) {
                    String state = genState(p1, p2);
                    if (history.contains(state))
                        continue;

                    p1.setTeam(t2);
                    p2.setTeam(t1);
                    history.add(state);
                    return true;
                }
            }
        }
        return false;
    }

    private String genState(Player p1, Player p2) {
        StringBuilder res = new StringBuilder("");
        for (int i = 0; i < players.length; i++) {
            Player p = players[i];
            Team t = p.getTeam();
            if (p == p1)
                t = p2.getTeam();
            if (p == p2)
                t = p1.getTeam();
            res.append(new Integer(t.getID()).toString());
            res.append(";");
        }
        return res.toString();
    }

}
