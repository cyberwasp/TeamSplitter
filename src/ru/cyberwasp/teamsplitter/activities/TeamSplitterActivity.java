package ru.cyberwasp.teamsplitter.activities;

import java.util.Arrays;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.adapters.SelectPlayersAdapter;
import ru.cyberwasp.teamsplitter.db.DataSource;
import ru.cyberwasp.teamsplitter.views.SelectPlayerView;
import ru.cyberwasp.teamsplitter.views.SelectPlayersView;
import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TextView;

public class TeamSplitterActivity extends Activity {
    
	private SelectPlayersView view;
	private DataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new SelectPlayersView(this);
		datasource = new DataSource(this);
		datasource.open();
	    Player players[] = getAllPlayers(); 
		view.setPlayers(players);
		setContentView(view);
		view.getSplitButton().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getSplitResult();
			}
		});
		registerForContextMenu(view.getGrid());
	}
    
	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		if (v == view.getGrid()) {
			menu.setHeaderTitle("Operations");
			menu.add(Menu.NONE, 0, 0, "Edit");
			menu.add(Menu.NONE, 1, 0, "Delete");
		}
	}	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	  switch (item.getItemId()) {
	  	case 0: editPlayer(info.position); break;
	  	case 1: deletePlayer(info.position); break;
	  }	  
	  return true;
	}	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(0, 0, 0, "New player");
        item.setIcon(R.drawable.ic_menu_add);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0: addPlayer(); break; 
        }
        return true;
    }   
    
	private void getSplitResult(){
		Intent intent = new Intent(this, TeamSplitterResultActivity.class);
    	intent.putExtra(TeamSplitterResultActivity.PARAM_NAME_TEAM_COUNT, this.getTeamCount());
    	intent.putExtra(TeamSplitterResultActivity.PARAM_NAME_SELECTED_IDS, this.getSelectedIds());
    	startActivity(intent);
	}
	
	private void editPlayer(int position) {
		Player player = getPlayerByPosition(position);
       	Intent intent = new Intent(this, PlayerEditorActivity.class);
    	intent.putExtra(PlayerEditorActivity.PARAM_NAME_PLAYER_ID, player.getId());
    	startActivity(intent);
	}

	private void deletePlayer(int position) {
		Player player = getPlayerByPosition(position);
		datasource.delete(player);		
	}

	private void addPlayer() {
		Intent intent = new Intent(this, PlayerEditorActivity.class);
		startActivity(intent);
	}

	private Player getPlayerByPosition(int position) {
		return (Player)view.getGrid().getItemAtPosition(position);
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
		return Arrays.copyOf(res, j);
	}

	public Integer getTeamCount() {
		TextView textView = (TextView)view.getNumOfTeams().getSelectedView();
		return new Integer(textView.getText().toString());
	}

	private Player[] getAllPlayers() {
		return datasource.getAllPlayers();
	}
	
	
}