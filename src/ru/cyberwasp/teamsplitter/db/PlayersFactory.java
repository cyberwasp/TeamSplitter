package ru.cyberwasp.teamsplitter;

import android.content.Context;
import android.database.Cursor;

public class PlayersFactory {
	
	private PlayersDataBase db;

	public PlayersFactory(Context context){
		this.db = new PlayersDataBase(context);
	}
	
	private String getCondition(int ids[]){
		if (ids == null) 
			return "";
		else {
			String res = " where _id in (-1";
			for (int i = 0; i < ids.length; i++) {
				res  += "," + ids[i];  
			}
			res += ")"; 
			return res;
		}
	}
	
	
	public Player[] getAllPlayers(){
		return getPlayers(null);
	}
	
	public Player[] getPlayers(int ids[]){
		Cursor cursor = db.getPlayers(getCondition(ids));
		cursor.moveToFirst();
		Player res[] = new Player[cursor.getCount()];
		int idIndex = cursor.getColumnIndex(PlayersDataBase.PlayersTable.Columns._ID);
		int nameIndex = cursor.getColumnIndex(PlayersDataBase.PlayersTable.Columns.NAME);
		int metricIndex = cursor.getColumnIndex(PlayersDataBase.PlayersTable.Columns.METRIC);
		while (!cursor.isAfterLast()){
			int id = cursor.getInt(idIndex);
			String name = cursor.getString(nameIndex);
			double metric = cursor.getDouble(metricIndex);
			res[cursor.getPosition()] = new Player(id, name, metric);
			cursor.moveToNext();
		}
		cursor.close();
		return res;
	}
	
	
}
