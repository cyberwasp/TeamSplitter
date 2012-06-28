package ru.cyberwasp.teamsplitter.views;

import ru.cyberwasp.teamsplitter.Player;
import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlayerEditorView extends LinearLayout {

	private TextView nameCaption;
	private TextView nameEdit;
	private TextView metricEdit;
	private TextView metricCaption;
	private Player player;
	
	public PlayerEditorView(Context context) {
		super(context);
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT); 

		nameCaption = new TextView(context);
		nameCaption.setText("Enter player Name: ");
		addView(nameCaption, lp);
		nameEdit = new EditText(context);
		addView(nameEdit, lp);

	    metricCaption = new TextView(context);
		metricCaption.setText("Enter player Name: ");
		addView(metricCaption, lp);
		metricEdit = new EditText(context);
		addView(metricEdit, lp);
	}

	public void setPlayer(Player player)
	{
		this.player = player;
		nameEdit.setText(player.getName());
		metricEdit.setText(new Double(player.getMetric()).toString());	
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	
	
}
