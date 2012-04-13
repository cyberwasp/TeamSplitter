package ru.cyberwasp.teamsplitter;

import java.util.ArrayList;
import java.util.List;

public class Team {
	
	private List<Player> players = new ArrayList<Player>();

	public Team(List<Player> players, int[] playersInTeams, int j) {
		for (int i = 0; i < playersInTeams.length; i++) {
			if (playersInTeams[i] == j)
				Add(players.get(i));
		}
	}

	private void Add(Player player) {
		this.players.add(player);
	}

	private static Double calcMediane(int[] data){
		Double res = 0.0;
		for (int i = 0; i < data.length; i++) {
			res += data[i];
		}
		return res / data.length;
	}
	
	private static Double calcStandartDeviation(int[] data){
		Double res = 0.0;
		Double mediane = calcMediane(data);
		for (int i = 0; i < data.length; i++) {
			res += Math.pow(data[i] - mediane, 2);
		}
		return res / data.length;
	}
	
	private static int[] calcMetrix(List<Player> players, int[] playersInTeams, int teamCount){
		int[] res = new int[teamCount];
		for (int i = 0; i < players.size(); i++) {
			res[playersInTeams[i]] += players.get(i).getMetrix();
		}
		return res;
	}
	
	public static List<Team> split(List<Player> players, int teamCount)
	{
		List<Team> res = new ArrayList<Team>();
		
		int playersInTeams[] = new int[players.size()];
		
		Double minDeviation = Double.MAX_VALUE;  
		
		for (int i = 0; i < (int)Math.pow(teamCount, players.size()); i++){
			int state = i;
			int counter = 0;
			while (state >= 0){
				playersInTeams[counter] = state % teamCount;
				state = state / teamCount;
				counter += 1;
			}
			
			int[] metrixes = calcMetrix(players, playersInTeams, teamCount);
			
			Double deviation = calcStandartDeviation(metrixes);
			
			if (deviation < minDeviation){
				res.clear();
				for (int j = 0; j < teamCount ; j++) {
					res.add(new Team(players, playersInTeams, j));
				}
			}
		}
		return res;
	}
}