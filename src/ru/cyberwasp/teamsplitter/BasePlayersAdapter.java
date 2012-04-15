package ru.cyberwasp.teamsplitter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public abstract class BasePlayersAdapter extends BaseAdapter {

		private final Player players[];
		private final Context context;

		public BasePlayersAdapter(Context context, Player players[]) {
			this.context = context;
			this.players = players;
		}

		public int getCount() {
			return players.length;
		}

		public Object getItem(int position) {
			return players[position];
		}

		public long getItemId(int position) {
			return 0;
		}

		public Player[] getPlayers() {
			return players;
		}

		public Context getContext() {
			return context;
		}

}
