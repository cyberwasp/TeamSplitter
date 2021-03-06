package ru.cyberwasp.teamsplitter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.cyberwasp.teamsplitter.algorithms.BubbleSearch;

public class Team {
    private static long maxID = 0;
    private List<Player> players = new ArrayList<Player>();
    private int num;
    private long id;

    public Team(int num, Player players[]) {
        this.num = num;
        if (players != null)
            this.players.addAll(Arrays.asList(players));
        genID();
    }

    public Team(int num, Player players[], int[] playersInTeams, int teamIndex) {
        this.num = num;
        for (int i = 0; i < playersInTeams.length; i++) {
            if (playersInTeams[i] == teamIndex)
                add(players[i]);
        }
        genID();
    }

    public Team(int num, Player players[], int[] playersIndexes) {
        this.num = num;
        for (int i = 0; i < playersIndexes.length; i++) {
            add(players[playersIndexes[i - 1]]);
        }
        genID();
    }

    public Team(int num) {
        this.num = num;
        genID();
    }

    private void genID() {
        id = maxID;
        maxID++;
    }

    public void add(Player player) {
        if (!players.contains(player)) {
            players.add(player);
            player.setTeam(this);
        }
    }

    public void remove(Player player) {
        if (players.contains(player)) {
            players.remove(player);
            player.setTeam(null);
        }
    }

    public static Team[] split(Player players[], int teamCount) {
        // return new ExchaustiveSearch().split(players, teamCount);
        return new BubbleSearch().split(players, teamCount);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    public int size() {
        return players.size();
    }

    public double getMetric() {
        double res = 0;
        for (Player player : players) {
            res += player.getMetric();
        }
        return res;
    }

    public String getInfo() {
        DecimalFormat fmt = new DecimalFormat("#.##");
        String metric = fmt.format(getMetric());
        return "Team " + num + " (" + metric + ") " + size() + " memeber(s)";
    }

    public long getID() {
        return id;
    }
}
