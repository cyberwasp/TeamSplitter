package ru.cyberwasp.teamsplitter.test;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.Team;

import junit.framework.TestCase;

public class TeamTestCase extends TestCase {

	public void testTeam() {

		Player p1 = new Player();
		Player p2 = new Player();
		Player p3 = new Player();
		
		Player players[] = {p1, p2, p3};

		Team team;
		
		team = new Team(players);
		assertTrue(team.hasPlayer(p2));
		assertTrue(team.hasPlayer(p3));
		assertTrue(team.hasPlayer(p1));
		
		int playerInTeams[] = {0, 1, 1};
		team = new Team(players, playerInTeams, 1);
		assertTrue(team.hasPlayer(p2));
		assertTrue(team.hasPlayer(p3));
		assertFalse(team.hasPlayer(p1));
	}

	public void testAdd() {
		Team team = new Team(null);
		Player player = new Player();
		team.Add(player);
		assertTrue(team.hasPlayer(player));
	}

	public void testCalcMediane() {
		int data[] = {10, 5, 5, 15, 90};
		assertEquals(25.0, Team.calcMediane(data));
	}

	public void testCalcSquareOfStandartDeviation() {
		int data[] = {10, 5, 5, 15, 90};
		assertEquals(1070.0, Team.calcSquareOfStandartDeviation(data));
	}

	public void testCalcMetric() {

		Player p1 = new Player(10);
		Player p2 = new Player(11);
		Player p3 = new Player(12);
		
		Player players[] = {p1, p2, p3};
		int playersInTeams[] = {0, 0, 1};
		
		int metrics[] = Team.calcMetric(players, playersInTeams, 2);
		
		assertEquals(21, metrics[0]);
		assertEquals(12, metrics[1]);
	}

	public void testSplit() {
		Player p1 = new Player(10);
		Player p2 = new Player(11);
		Player p3 = new Player(12);
		Player players[] = {p1, p2, p3};
		Team teams[] = Team.split(players, 2);
		//player1 and player2 in same team
		assertFalse(teams[1].hasPlayer(p1) ^ teams[1].hasPlayer(p2));
		assertFalse(teams[0].hasPlayer(p1) ^ teams[0].hasPlayer(p2));
		//player1 and player3 in different team
		assertTrue(teams[1].hasPlayer(p1) ^ teams[1].hasPlayer(p3));
		assertTrue(teams[0].hasPlayer(p1) ^ teams[0].hasPlayer(p3));
		
		Player players2[] = {
				new Player(1), new Player(1), new Player(1), new Player(1), new Player(1),
				new Player(1), new Player(1), new Player(1), new Player(1), new Player(1)};
		Team teams2[] = Team.split(players2, 2);
		assertEquals(5, teams2[0].size());
		assertEquals(5, teams2[1].size());

// 1400000 variants :)		
//		Player players3[] = {
//				new Player(1), new Player(1), new Player(1), new Player(1), new Player(1),
//				new Player(1), new Player(1), new Player(1), new Player(1), new Player(1), 
//				new Player(1), new Player(1), new Player(1), new Player(1), new Player(1)};
//		Team teams3[] = Team.split(players3, 3);
//		assertEquals(5, teams3[0].size());
//		assertEquals(5, teams3[1].size());
//		assertEquals(5, teams3[3].size());
	}

}
