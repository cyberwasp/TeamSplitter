package ru.cyberwasp.teamsplitter.adapters;

import ru.cyberwasp.teamsplitter.Player;
import android.content.Context;
import android.widget.BaseAdapter;

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
			return players[position].getId();
		}

		public Player[] getPlayers() {
			return players;
		}

		public Context getContext() {
			return context;
		}

}
