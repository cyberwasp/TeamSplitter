package ru.cyberwasp.teamsplitter;

import android.content.Context;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SelectPalyersView extends LinearLayout {

	private TextView caption;
	private GridView grid;
	private Button button;
	private Spinner numOfTeams;

	public SelectPalyersView(Context context) {
		super(context);
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT); 
		caption = new TextView(context);
		caption.setText("Select players");
		addView(caption, lp);
		grid = new GridView(context);
		grid.setVerticalSpacing(2);
		grid.setHorizontalSpacing(2);
		grid.setGravity(Gravity.CENTER);
		addView(grid, lp);
		
		LinearLayout bottom = new LinearLayout(context);
		bottom.setOrientation(HORIZONTAL);
		bottom.setLayoutParams(lp);
		
		TextView numOfTeamsCaption = new TextView(context);
		numOfTeamsCaption.setText("Number of teams:");
		bottom.addView(numOfTeamsCaption);

		numOfTeams = new Spinner(context);
		String nums[] = {"2", "3", "4", "5"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, nums);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		numOfTeams.setAdapter(adapter);
		bottom.addView(numOfTeams);
		
		button = new Button(context);
		button.setText("Split");
		bottom.addView(button, lp);
		
		addView(bottom);
	}

	public void setPlayers(Player[] players) {
		BasePlayersAdapter adapter =  new SelectPlayersAdapter(getContext(), players);
		grid.setAdapter(adapter);
	}

	public Button getSplitButton() {
		return button;
	}

	public Spinner getNumOfTeams() {
		return numOfTeams;
	}

	public void setNumOfTeams(Spinner numOfTeams) {
		this.numOfTeams = numOfTeams;
	}

	public GridView getGrid() {
		return grid;
	}

}
