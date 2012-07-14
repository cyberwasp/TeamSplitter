package ru.cyberwasp.teamsplitter.views;

import ru.cyberwasp.teamsplitter.Team;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

public class TeamView extends TextView {

	public TeamView(Context context) {
		super(context);
        setTextColor(Color.BLACK);
        setBackgroundColor(Color.GREEN);
	}
	
	public void setTeam(Team team){
		setText(team.getInfo());
	}
}
