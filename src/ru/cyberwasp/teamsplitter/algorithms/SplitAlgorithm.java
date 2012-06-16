package ru.cyberwasp.teamsplitter.algorithms;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.Team;

public interface SplitAlgorithm {	
	public Team[] split(final Player players[], int teamCount); 
}
