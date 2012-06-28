package ru.cyberwasp.teamsplitter.activities;

import ru.cyberwasp.teamsplitter.Player;
import ru.cyberwasp.teamsplitter.db.DataSource;
import ru.cyberwasp.teamsplitter.db.DBHelper;
import ru.cyberwasp.teamsplitter.views.PlayerEditorView;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class PlayerEditorActivity extends Activity {

	public static final String PARAM_NAME_PLAYER_ID = "PlayerID";
	
	private PlayerEditorView view;
	private Player player;
	private DataSource datasource;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new PlayerEditorView(this);
		datasource = new DataSource(this);
		if (isSetPlayerID())
			player = datasource.getPlayer(getPlayerID());
		else
			player = new Player();
	    view.setPlayer(player);
		setContentView(view);
    }
	
	@Override
	protected void onPause() {
		super.onPause();
		saveState();
		datasource.close();
	}
	
	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	private int getPlayerID() {
		Bundle extras = getIntent().getExtras();
		return extras.getInt(PARAM_NAME_PLAYER_ID);
	}

	private boolean isSetPlayerID() {
		Bundle extras = getIntent().getExtras();
		return extras.containsKey(PARAM_NAME_PLAYER_ID);
	}

	private OnClickListener okClick = new OnClickListener() {
	    public void onClick(View v) {
	      saveState();
	      setResult(RESULT_OK);
	    }
	};	

	private OnClickListener cancelClick = new OnClickListener() {
	    public void onClick(View v) {
	      setResult(RESULT_CANCELED);
	    }
	};	
	
	private void saveState() {
		datasource.save(player);
	}

}
