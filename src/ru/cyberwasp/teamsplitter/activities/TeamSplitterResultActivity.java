package ru.cyberwasp.teamsplitter.activities;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.Team;
import ru.cyberwasp.teamsplitter.db.PlayersFactory;
import ru.cyberwasp.teamsplitter.views.TeamSplitterResultView;
import android.app.Activity;
import android.os.Bundle;

public class TeamSplitterResultActivity extends Activity {
    
	public static final String PARAM_NAME_MAX_DIFF_OF_COUNT = "MaxDiffOfCount";
	public static final String PARAM_NAME_TEAM_COUNT = "TeamCount";
	public static final String PARAM_NAME_SELECTED_IDS = "SelectedIDs";
	
	private TeamSplitterResultView view;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new TeamSplitterResultView(this);
	    Team teams[] = splitSelectedPlayers(); 
		view.setTeams(teams);
		setContentView(view);
    }

	private Team[] splitSelectedPlayers() {
		Player[] players = getSelectedPlayers();
		int teamCount = getTeamCount();
		int maxDiffOfCount = getMaxDiffOfCount();		
		return Team.split(players, teamCount, maxDiffOfCount);
	}
	
	private int getTeamCount() {
		Bundle extras = getIntent().getExtras();
		return extras.getInt(PARAM_NAME_TEAM_COUNT);
	}

	private int getMaxDiffOfCount() {
		Bundle extras = getIntent().getExtras();
		return extras.getInt(PARAM_NAME_MAX_DIFF_OF_COUNT);
	}
	
	private Player[] getSelectedPlayers() {
		Bundle extras = getIntent().getExtras();
		int ids[] = extras.getIntArray(PARAM_NAME_SELECTED_IDS);
		PlayersFactory factory = new PlayersFactory(this);
		return factory.getPlayers(ids);
	}
}