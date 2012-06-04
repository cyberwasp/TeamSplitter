package ru.cyberwasp.teamsplitter.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlayersDataBase {
	
	public static final String TAG = "PlayersDataBase";

    public static class PlayersTable{
		public static String NAME = "players";
		public static String ALIAS = "p";
		public static class Columns{
			public static String _ID = "_ID";
			public static String NAME = "NAME";
			public static String METRIC = "METRIC";
		}
	}

	private static class DatabaseHelper extends SQLiteOpenHelper{
		
		private static final String DATABASE_NAME = "players.db";
		private static final int DATABASE_VERSION = 1;

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + PlayersDataBase.PlayersTable.NAME + " (" 
                    + PlayersDataBase.PlayersTable.Columns._ID + " INTEGER PRIMARY KEY,"
                    + PlayersDataBase.PlayersTable.Columns.NAME + " TEXT,"
                    + PlayersDataBase.PlayersTable.Columns.METRIC + " DOUBLE "
                    + ");");
            
            db.execSQL("Insert into players (_id, name, metric) values (0, \"player one\", 10)");
            db.execSQL("Insert into players (_id, name, metric) values (1, \"player two\", 20)");
            db.execSQL("Insert into players (_id, name, metric) values (2, \"player three\", 11)");
            db.execSQL("Insert into players (_id, name, metric) values (3, \"player four\", 22)");
            db.execSQL("Insert into players (_id, name, metric) values (4, \"player five\", 44)");
            db.execSQL("Insert into players (_id, name, metric) values (5, \"player six\", 18)");
		}
            
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			//TODO: upgrade database without lost data?
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " +  PlayersDataBase.PlayersTable.NAME);
            onCreate(db);
		}
		
	}
	
	public Cursor getPlayers(String condition){
    	String sql = "select * from " + PlayersDataBase.PlayersTable.NAME + " " + condition;
    	return database.rawQuery(sql, null);
	}
	
	private SQLiteDatabase database; 
    
    public PlayersDataBase(Context context){
    	DatabaseHelper mOpenHelper = new DatabaseHelper(context);
    	database = mOpenHelper.getWritableDatabase();
    }

	public SQLiteDatabase getRealDataBase(){
		return database;
	}

}
