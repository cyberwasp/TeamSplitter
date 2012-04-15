package ru.cyberwasp.teamsplitter;

import android.app.Activity;
import android.os.Bundle;

public class TeamSplitterResultActivity extends Activity {
    
	public static String PARAM_NAME_TEAM_COUNT = "TeamCount";
	public static String PARAM_NAME_SELECTED_IDS = "SelectedIDs";
	
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
		return Team.split(players, teamCount);
	}
	
	private int getTeamCount() {
		Bundle extras = getIntent().getExtras();
		return extras.getInt(PARAM_NAME_TEAM_COUNT);
	}

	private Player[] getSelectedPlayers() {
		Bundle extras = getIntent().getExtras();
		int ids[] = extras.getIntArray(PARAM_NAME_SELECTED_IDS);
		PlayersFactory factory = new PlayersFactory(this);
		return factory.getPlayers(ids);
	}
}
