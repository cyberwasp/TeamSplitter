package ru.cyberwasp.teamsplitter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.cyberwasp.teamsplitter.algorithms.BubbleSearch;

public class Team {
	private static int maxID = 0;
	private List<Player> players = new ArrayList<Player>();
	private int num;
	private int id;

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
			add(players[playersIndexes[i-1]]);
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
		int idx = this.players.indexOf(player);
		if (idx < 0)
		{
			this.players.add(player);
			player.setTeam(this);
		}
	}

	public void remove(Player player) {
		int idx = this.players.indexOf(player);
		if (idx >= 0)
		{
			this.players.remove(idx);
			player.setTeam(null);
		}
	}

	public static Team[] split(Player players[], int teamCount, int maxDiffPlayerCount)
	{
		//return new ExchaustiveSearch().split(players, teamCount, maxDiffPlayerCount);
		return new BubbleSearch().split(players, teamCount, maxDiffPlayerCount);
	}
	
	public Player[] getPlayers(){
		return players.toArray(new Player[0]);		
	}
	
	public List<Player> getPlayerList()
	{
		return players;
	}

	public boolean hasPlayer(Player player) {
		return players.contains(player);
	}

	public int size() {
		return players.size();
	}
	
	public int getMetric(){
		int res = 0;
		for (int i = 0; i < players.size(); i++) {
			res += players.get(i).getMetric();
		}
		return res;
	}
	
	public String getInfo(){
		DecimalFormat fmt = new DecimalFormat("#.##");
		String metric = fmt.format(getMetric());
		return "Team " + num + " (metric = " + metric + ")";
	}
	
	public int getID(){
		return id;
	}
}