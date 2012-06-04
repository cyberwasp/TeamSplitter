package ru.cyberwasp.teamsplitter.views;

import ru.cyberwasp.teamsplitter.Team;
import ru.cyberwasp.teamsplitter.adapters.ResultPlayersAdapter;
import android.content.Context;
import android.graphics.Color;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TeamSplitterResultView extends LinearLayout {

	private Team[] teams;
	private TextView mainCaption;
	private GridView grids[];
	private TextView captions[];

	public TeamSplitterResultView(Context context) {
		super(context);
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT); 
		mainCaption = new TextView(context);
		mainCaption.setText("Commands:");
		addView(mainCaption, lp);
	}

	public Team[] getTeams() {
		return teams;
	}

	public void setTeams(Team[] teams) {
		this.teams = teams;

		if (captions != null)
			for (int i = 0; i < captions.length; i++) {
				removeView(grids[i]);			
		}

		if (grids != null) 
			for (int i = 0; i < grids.length; i++) {
				removeView(grids[i]);			
			}
		
		grids = new GridView[teams.length];
		captions = new TextView[teams.length];
		
		for (int i = 0; i < teams.length; i++) {
			captions[i] = new TextView(getContext());
			captions[i].setText(teams[i].getInfo());
			captions[i].setTextColor(Color.BLACK);
			captions[i].setBackgroundColor(Color.GREEN);
			grids[i] = new GridView(getContext());
			ResultPlayersAdapter adapter = new ResultPlayersAdapter(getContext(), teams[i].getPlayers());
			grids[i].setAdapter(adapter);
			addView(captions[i]);
			addView(grids[i]);
			
		}
	}
}
