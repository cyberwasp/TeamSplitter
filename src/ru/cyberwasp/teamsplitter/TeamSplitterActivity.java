package ru.cyberwasp.teamsplitter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

public class TeamSplitterActivity extends Activity {
    
	private SelectPalyersView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new SelectPalyersView(this);
	    Player players[] = getAllPlayers(); 
		view.setPlayers(players);
		setContentView(view);
    }

	private Player[] getAllPlayers() {
		PlayersDataBase db = new PlayersDataBase(this);
		Cursor cursor = db.getPlayers();
		cursor.moveToFirst();
		Player res[] = new Player[cursor.getCount()];
		int nameIndex = cursor.getColumnIndex(PlayersDataBase.PlayersTable.Columns.NAME);
		int metricIndex = cursor.getColumnIndex(PlayersDataBase.PlayersTable.Columns.METRIC);
		while (!cursor.isAfterLast()){
			String name = cursor.getString(nameIndex);
			double metric = cursor.getDouble(metricIndex);
			res[cursor.getPosition()] = new Player(name, metric);
			cursor.moveToNext();
		}
		cursor.close();
		return res;
	}
}