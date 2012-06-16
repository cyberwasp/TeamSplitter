package ru.cyberwasp.teamsplitter.views;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.adapters.BasePlayersAdapter;
import ru.cyberwasp.teamsplitter.adapters.SelectPlayersAdapter;
import android.content.Context;
import android.util.Pair;
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
	
	private Pair<LinearLayout, Spinner> addComboboxWithCaption(String caption, String [] data){
		
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);		
		LinearLayout layout = new LinearLayout(getContext());
		layout.setOrientation(HORIZONTAL);
		layout.setLayoutParams(lp);
		
		TextView captionView = new TextView(getContext());
		captionView.setText(caption);
		layout.addView(captionView);

		Spinner spinner = new Spinner(getContext());
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setLayoutParams(lp);
		layout.addView(spinner);
		
		return new Pair<LinearLayout, Spinner>(layout, spinner);
	}

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
		
		String numbers[] = {"2", "3", "4", "5"};
		Pair<LinearLayout, Spinner> pair1 = addComboboxWithCaption("Number of teams: ", numbers);
		addView(pair1.first);
		numOfTeams = pair1.second;
		
		button = new Button(context);
		button.setText("Split");
		addView(button, lp);
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

	public GridView getGrid() {
		return grid;
	}

}
