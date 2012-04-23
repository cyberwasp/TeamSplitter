package ru.cyberwasp.teamsplitter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Team {
	
	private List<Player> players = new ArrayList<Player>();
	private int num;

	public Team(int num, Player players[]) {
		this.num = num;
		if (players != null)
			this.players.addAll(Arrays.asList(players));
	}
	
	public Team(int num, Player players[], int[] playersInTeams, int teamIndex) {
		this.num = num;
		for (int i = 0; i < playersInTeams.length; i++) {
			if (playersInTeams[i] == teamIndex)
				Add(players[i]);
		}
	}

	public void Add(Player player) {
		this.players.add(player);
	}

	public static double calcMediane(int[] data){
		double res = 0.0;
		for (int i = 0; i < data.length; i++) {
			res += data[i];
		}
		return res / data.length;
	}
	
	public static double calcSquareOfStandartDeviation(int[] data){
		double res = 0.0;
		double mediane = calcMediane(data);
		for (int i = 0; i < data.length; i++) {
			res += Math.pow(data[i] - mediane, 2);
		}
		return res / data.length;
	}
	
	public static int[] calcMetric(Player players[], int[] playersInTeams, int teamCount){
		int[] res = new int[teamCount];
		for (int i = 0; i < players.length; i++) {
			res[playersInTeams[i]] += players[i].getMetric();
		}
		return res;
	}
	
	public static Team[] split(Player players[], int teamCount, int maxDiffPlayerCount)
	{
		Team res[] = new Team[teamCount];
		
		int playersInTeams[] = new int[players.length];
		
		double minDeviation = Double.MAX_VALUE;
		
		int cntInTeam[] = new int[teamCount];
		
		for (int i = 0; i < (int)Math.pow(teamCount, players.length); i++){
			
			int state = i;
			
			int counter = 0;
			
			Arrays.fill(cntInTeam, 0);
			
			while (state > 0) {
				playersInTeams[counter] = state % teamCount;
				cntInTeam[state % teamCount] += 1;
				state = state / teamCount;
				counter += 1;
			} 
			
			while (counter < playersInTeams.length){
				playersInTeams[counter] = 0;
				counter += 1;
				cntInTeam[0] += 1;
			} 
			
			Arrays.sort(cntInTeam);
			
			if ((cntInTeam[cntInTeam.length - 1] - cntInTeam[0]) > maxDiffPlayerCount) 
				continue;
			
			int[] metrics = calcMetric(players, playersInTeams, teamCount);
			
			double deviation = calcSquareOfStandartDeviation(metrics);
			
			if (deviation < minDeviation){
				for (int j = 0; j < teamCount ; j++) {
					res[j] = new Team(j, players, playersInTeams, j);
				}
				minDeviation = deviation;
			}
		}
		return res;
	}
	
	public static Team[] split2(Player players[], int teamCount, int maxDiffPlayerCount){
		List<int[]> combinations = new ArrayList<int[]>();
		int avaragePlayerInTeam = players.length / teamCount; 
		for (int i = avaragePlayerInTeam - maxDiffPlayerCount; i <= avaragePlayerInTeam + maxDiffPlayerCount;
				i++)
		{
			combinations.addAll(genCombnations(players.length, i));
		}
		Collections.sort(combinations, new PlayerComparator(players));
	}
	

	private static int[] genRange(int i, int j) {
		int res[] = new int[j - i + 1];
		for (int k = 0; k < res.length; k++) {
			res[k] = i + k;
		}
	    return res;
	}

	public static List<int[]> genCombnations(int m, int n) {
		List<int[]> res = new ArrayList<int[]>();  
	    int a[] = genRange(1, m);
	    if (m == n) {
	    	res.add(a);
	    	return res;
	    }      
	    else{
	    	int p = n;
	    	while (p >= 1){
	    		res.add(Arrays.copyOf(a, n));
	    	    if (a[n-1] == m){
	    	    	p -= 1;
	    	    }
	    	    else{
	    	        p = n;
	    	    }
	    	    if (p >=1){
	    	    	for (int i = n; i >= p; i--) {
	    	    		a[i-1] = a[p-1] + i - p + 1;
	    	    	}
	    	    }
	    	}
	    }
		return res;
	}


	public Player[] getPlayers(){
		return (Player[]) players.toArray(new Player[0]);		
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
}