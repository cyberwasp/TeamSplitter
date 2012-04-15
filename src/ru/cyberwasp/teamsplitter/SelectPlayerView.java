package ru.cyberwasp.teamsplitter;

import android.content.Context;
import android.widget.CheckBox;

public class SelectPlayerView extends CheckBox{

	private final Player player;

	public SelectPlayerView(Context context, Player player) {
		super(context);
		this.player = player;
		setChecked(false);
		setText(player.getName());
	}

	public Player getPlayer() {
		return player;
	}

}
