package ru.cyberwasp.teamsplitter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class PlayersAdapter extends BaseAdapter {

		private final Player players[];
		private final Context context;

		public PlayersAdapter(Context context, Player players[]) {
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

		public View getView(int position, View convertView, ViewGroup parent) {
			SelectPlayerView view;
			if (convertView == null) {
				view = new SelectPlayerView(context, players[position]);
			}
			else {
				view = (SelectPlayerView) convertView;
			}
			
			view.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.FILL_PARENT, GridView.LayoutParams.WRAP_CONTENT));
			return view;
		}
}
