package ru.cyberwasp.teamsplitter.adapters;

import ru.cyberwasp.teamsplitter.Team;
import ru.cyberwasp.teamsplitter.views.ResultPlayerView;
import ru.cyberwasp.teamsplitter.views.TeamView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class TeamListAdapter extends BaseExpandableListAdapter {

	private Team[] teams;
	private final Context context;

	public TeamListAdapter(Context context,  Team[] teams) {
		super();
		this.context = context;
		this.teams = teams;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return teams[groupPosition].getPlayerList().get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return teams[groupPosition].getPlayerList().get(childPosition).getId();
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ResultPlayerView view = (convertView == null)?new ResultPlayerView(context):(ResultPlayerView) convertView; 
		view.setPlayer(teams[groupPosition].getPlayerList().get(childPosition));
		return view;
	}

	public int getChildrenCount(int groupPosition) {
		return teams[groupPosition].getPlayerList().size();
	}

	public Object getGroup(int groupPosition) {
		return teams[groupPosition];
	}

	public int getGroupCount() {
		return teams.length;
	}

	public long getGroupId(int groupPosition) {
		return teams[groupPosition].getID();
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TeamView view = (convertView == null)?new TeamView(context):(TeamView) convertView; 
		view.setTeam(teams[groupPosition]);
		return view;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}
