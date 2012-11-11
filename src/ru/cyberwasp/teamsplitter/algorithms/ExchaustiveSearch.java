package ru.cyberwasp.teamsplitter.algorithms;

import java.util.Arrays;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.Team;

public class ExchaustiveSearch implements SplitAlgorithm {

    public double[] calcMetric(Player players[], int[] playersInTeams,
            int teamCount) {
        double[] res = new double[teamCount];
        for (int i = 0; i < players.length; i++) {
            res[playersInTeams[i]] += players[i].getMetric();
        }
        return res;
    }

    public Team[] split(final Player[] players, int teamCount) {

        Team res[] = new Team[teamCount];

        int playersInTeams[] = new int[players.length];

        double minDeviation = Double.MAX_VALUE;

        int cntInTeam[] = new int[teamCount];

        for (int i = 0; i < (int) Math.pow(teamCount, players.length); i++) {

            int state = i;

            int counter = 0;

            Arrays.fill(cntInTeam, 0);

            while (state > 0) {
                playersInTeams[counter] = state % teamCount;
                cntInTeam[state % teamCount] += 1;
                state = state / teamCount;
                counter += 1;
            }

            while (counter < playersInTeams.length) {
                playersInTeams[counter] = 0;
                counter += 1;
                cntInTeam[0] += 1;
            }

            Arrays.sort(cntInTeam);

            if ((cntInTeam[cntInTeam.length - 1] - cntInTeam[0]) > 1)
                continue;

            double[] metrics = calcMetric(players, playersInTeams, teamCount);

            double deviation = Utils.calcSquareOfStandardDeviation(metrics);

            if (deviation < minDeviation) {
                for (int j = 0; j < teamCount; j++) {
                    res[j] = new Team(j, players, playersInTeams, j);
                }
                minDeviation = deviation;
            }
        }
        return res;
    }
}
