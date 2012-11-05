package ru.cyberwasp.teamsplitter.views;

import java.util.Collections;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.R;
import ru.cyberwasp.teamsplitter.Team;
import ru.cyberwasp.teamsplitter.adapters.TeamListAdapter;
import android.content.Context;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TeamListView extends LinearLayout {

    private ExpandableListView teams;
    private TextView caption;
	private Button button;
 
    public TeamListView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        LinearLayout panel = new LinearLayout(context);
        panel.setOrientation(HORIZONTAL);
        panel.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
        panel.setGravity(Gravity.CENTER_VERTICAL);
        panel.setPadding(4, 4, 4, 4);
        addView(panel);
        
        caption = new TextView(context);
        caption.setText("Teams:  ");
        caption.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
        caption.setTextColor(getResources().getColor(R.color.toolbar_text_color));
        panel.addView(caption, lp);

        button = new Button(context);
        button.setText("Try resplit");
        panel.addView(button, lp1);

        teams = new ExpandableListView(context);
        teams.setBackgroundColor(getResources().getColor(R.color.background_color));
        teams.setCacheColorHint(DRAWING_CACHE_QUALITY_AUTO);
        addView(teams, lp1);
        teams.setGroupIndicator(null);
    }

    public Button getTryResplitButton() {
		return button;
	}

	public void setTeams(Team[] teams) {
        this.teams.setAdapter(new TeamListAdapter(getContext(), teams));
        for (int i = 0; i < teams.length; i++) {
        	Collections.sort(teams[i].getPlayers(), Player.comparatorByName);
            this.teams.expandGroup(i);
        }
    }
}
