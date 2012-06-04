package ru.cyberwasp.teamsplitter.test;

import junit.framework.TestCase;
import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.Team;

public class TeamTestCase extends TestCase {

	public void testTeam() {

		Player p1 = new Player();
		Player p2 = new Player();
		Player p3 = new Player();
		
		Player players[] = {p1, p2, p3};

		Team team;
		
		team = new Team(1, players);
		assertTrue(team.hasPlayer(p2));
		assertTrue(team.hasPlayer(p3));
		assertTrue(team.hasPlayer(p1));
		
		int playerInTeams[] = {0, 1, 1};
		team = new Team(1, players, playerInTeams, 1);
		assertTrue(team.hasPlayer(p2));
		assertTrue(team.hasPlayer(p3));
		assertFalse(team.hasPlayer(p1));
	}

	public void testAdd() {
		Team team = new Team(1, null);
		Player player = new Player();
		team.add(player);
		assertTrue(team.hasPlayer(player));
	}

	public void testSplit() {
	}
}
