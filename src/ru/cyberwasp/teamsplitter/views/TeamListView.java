package ru.cyberwasp.teamsplitter.views;

import ru.cyberwasp.teamsplitter.R;
import ru.cyberwasp.teamsplitter.Team;
import ru.cyberwasp.teamsplitter.adapters.TeamListAdapter;
import android.content.Context;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TeamListView extends LinearLayout {

    private ExpandableListView teams;
    private TextView mainCaption;
 
    public TeamListView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mainCaption = new TextView(context);
        mainCaption.setText("Teams:");
        mainCaption.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
        mainCaption.setTextColor(getResources().getColor(R.color.toolbar_text_color));
        addView(mainCaption, lp);
        teams = new ExpandableListView(context);
        teams.setBackgroundColor(getResources().getColor(R.color.background_color));
        teams.setCacheColorHint(DRAWING_CACHE_QUALITY_AUTO);
        addView(teams, lp);
        teams.setGroupIndicator(null);
    }

    public void setTeams(Team[] teams) {
        this.teams.setAdapter(new TeamListAdapter(getContext(), teams));
        for (int i = 0; i < teams.length; i++) {
            this.teams.expandGroup(i);
        }
    }
}