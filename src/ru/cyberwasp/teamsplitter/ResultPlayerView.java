package ru.cyberwasp.teamsplitter;

import android.content.Context;
import android.widget.TextView;

public class ResultPlayerView extends TextView {

	private Player player;

	public ResultPlayerView(Context context) {
		super(context);
	}

	public void setPlayer(Player player) {
		this.player = player;
		setText(player.getName());
	}
	
	public Player getPlayer(){
		return player;
	}

}
