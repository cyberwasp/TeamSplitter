package ru.cyberwasp.teamsplitter.activities;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.Team;
import ru.cyberwasp.teamsplitter.db.DataSource;
import ru.cyberwasp.teamsplitter.views.TeamListView;
import android.app.Activity;
import android.os.Bundle;

public class TeamSplitterResultActivity extends Activity {

	public static final String PARAM_NAME_TEAM_COUNT = "TeamCount";
	public static final String PARAM_NAME_SELECTED_IDS = "SelectedIDs";

	private TeamListView view;
	private DataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new TeamListView(this);
		datasource = new DataSource(this);
		datasource.open();
		Team teams[] = splitSelectedPlayers();
		view.setTeams(teams);
		setContentView(view);
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
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
		long ids[] = extras.getLongArray(PARAM_NAME_SELECTED_IDS);
		return datasource.getPlayers(ids);
	}
}