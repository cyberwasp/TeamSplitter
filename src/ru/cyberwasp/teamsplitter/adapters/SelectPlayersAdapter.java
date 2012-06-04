package ru.cyberwasp.teamsplitter.adapters;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.views.SelectPlayerView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class SelectPlayersAdapter extends BasePlayersAdapter {

	public SelectPlayersAdapter(Context context, Player[] players) {
		super(context, players);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		SelectPlayerView view;
		if (convertView == null) {
			view = new SelectPlayerView(getContext());
		}
		else {
			view = (SelectPlayerView) convertView;
		}
		view.setPlayer(getPlayers()[position]);
		return view;
	}

}
