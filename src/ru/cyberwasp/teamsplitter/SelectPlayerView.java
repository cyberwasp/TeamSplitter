package ru.cyberwasp.teamsplitter;

import java.text.DecimalFormat;

import android.content.Context;
import android.widget.CheckBox;

public class SelectPlayerView extends CheckBox{

	private Player player = null;

	public SelectPlayerView(Context context) {
		super(context);
		setChecked(true);
	}
	
	public void setPlayer(Player player){
		this.player = player;
		setText(player.getInfo());
	}

	public Player getPlayer() {
		return player;
	}

}
