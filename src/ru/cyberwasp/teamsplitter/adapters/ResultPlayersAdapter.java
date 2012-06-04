package ru.cyberwasp.teamsplitter.adapters;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.views.ResultPlayerView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

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
