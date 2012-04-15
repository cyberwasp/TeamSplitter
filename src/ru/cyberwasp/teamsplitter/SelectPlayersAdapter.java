package ru.cyberwasp.teamsplitter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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
		view.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.FILL_PARENT, GridView.LayoutParams.WRAP_CONTENT));
		return view;
	}

}
