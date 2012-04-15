package ru.cyberwasp.teamsplitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TeamSplitterActivity extends Activity {
    
	private SelectPalyersView view;

    class IntentOnClickListener implements OnClickListener{
    	
    	private final TeamSplitterActivity context;
		private final Class<?> cls;
		
		public IntentOnClickListener(TeamSplitterActivity context, Class<?> cls) {
			this.context = context;
			this.cls = cls;
		}
    	
    	public void onClick(View v){
        	Intent intent = new Intent(context, cls);
        	intent.putExtra(TeamSplitterResultActivity.PARAM_NAME_TEAM_COUNT, context.getTeamCount());
        	intent.putExtra(TeamSplitterResultActivity.PARAM_NAME_SELECTED_IDS, context.getSelectedIds());
        	startActivity(intent);
    	}
    }   
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new SelectPalyersView(this);
	    Player players[] = getAllPlayers(); 
		view.setPlayers(players);
		setContentView(view);
		OnClickListener l = new IntentOnClickListener(this, TeamSplitterResultActivity.class);
		view.getSplitButton().setOnClickListener(l);
    }

	public int[] getSelectedIds() {
		SelectPlayersAdapter adapter = (SelectPlayersAdapter) view.getGrid().getAdapter();
		int res[] = new int[adapter.getCount()];
		int j = 0;
		for (int i = 0; i < adapter.getCount(); i++) {
			SelectPlayerView selectPlayerView = (SelectPlayerView) view.getGrid().getChildAt(i);
			if (selectPlayerView.isChecked()){
				res[j] = selectPlayerView.getPlayer().getId();
				j += 1;
			}
		}
		return Arrays.copyOf(res, j + 1);
	}

	public Integer getTeamCount() {
		TextView textView = (TextView)view.getNumOfTeams().getSelectedView();
		return new Integer(textView.getText().toString());
	}

	private Player[] getAllPlayers() {
		PlayersFactory factory = new PlayersFactory(this);
		return factory.getAllPlayers();
	}
}