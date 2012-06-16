package ru.cyberwasp.teamsplitter.test.algorithms;

import junit.framework.TestCase;
import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.Team;
import ru.cyberwasp.teamsplitter.algorithms.ExchaustiveSearch;

public class ExchaustiveSearchTestCase extends TestCase 
{
	public void testCalcMetric() 
	{
		Player p1 = new Player(10);
		Player p2 = new Player(11);
		Player p3 = new Player(12);
		
		Player players[] = {p1, p2, p3};
		int playersInTeams[] = {0, 0, 1};
		
		ExchaustiveSearch s = new ExchaustiveSearch();
		double metrics[] = s.calcMetric(players, playersInTeams, 2);
		
		assertEquals(21.0, metrics[0]);
		assertEquals(12.0, metrics[1]);
	}
	
	public void testSplit() 
	{
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
	}
}
