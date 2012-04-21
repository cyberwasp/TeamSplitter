package ru.cyberwasp.teamsplitter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ResultPlayersAdapter extends BasePlayersAdapter {

	public ResultPlayersAdapter(Context context, Player[] players) {
		super(context, players);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ResultPlayerView view;
		if (convertView == null) {
			view = new ResultPlayerView(getContext());
		}
		else {
			view = (ResultPlayerView) convertView;
		}
		view.setPlayer(getPlayers()[position]);
		return view;
	}

}
