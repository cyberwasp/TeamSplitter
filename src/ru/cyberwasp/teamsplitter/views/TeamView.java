package ru.cyberwasp.teamsplitter.views;

import ru.cyberwasp.teamsplitter.R;
import ru.cyberwasp.teamsplitter.Team;
import android.content.Context;
import android.widget.TextView;

public class TeamView extends TextView {

    public TeamView(Context context) {
        super(context);
        setTextAppearance(context, R.style.MyTextAppearance);
        setBackgroundColor(getResources()
                .getColor(R.color.team_backgound_color));
    }

    public void setTeam(Team team) {
        setText(team.getInfo());
    }
}
