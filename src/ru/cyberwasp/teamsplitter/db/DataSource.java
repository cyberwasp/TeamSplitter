package ru.cyberwasp.teamsplitter.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ru.cyberwasp.teamsplitter.Player;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DataSource(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        db = null;
    }

    public boolean isOpened() {
        return db != null;
    }

    private String getCondition(long ids[]) {
        if (ids == null)
            return "";
        else {
            String res = "_id in (-1";
            for (long id : ids) {
                res += "," + id;
            }
            res += ")";
            return res;

        }
    }

    public Player[] getAllPlayers() {
        return getPlayers(null);
    }

    public Player[] getPlayers(long ids[]) {
        Cursor cursor = db.query(DBHelper.PlayersTable.NAME,
                DBHelper.PlayersTable.ALL_COLUMNS, getCondition(ids), null,
                null, null, null, null);
        cursor.moveToFirst();
        Player res[] = new Player[cursor.getCount()];
        int idIndex = cursor.getColumnIndex(DBHelper.PlayersTable.Columns._ID);
        int nameIndex = cursor
                .getColumnIndex(DBHelper.PlayersTable.Columns.NAME);
        int metricIndex = cursor
                .getColumnIndex(DBHelper.PlayersTable.Columns.METRIC);
        while (!cursor.isAfterLast()) {
            long id = cursor.getLong(idIndex);
            String name = cursor.getString(nameIndex);
            double metric = cursor.getDouble(metricIndex);
            res[cursor.getPosition()] = new Player(id, name, metric);
            cursor.moveToNext();
        }
        cursor.close();
        return res;
    }

    public Player getPlayer(long id) {
        long ids[] = new long[1];
        ids[0] = id;
        Player players[] = getPlayers(ids);
        if (players.length == 0)
            return new Player();
        return players[0];
    }

    public void save(Player player) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.PlayersTable.Columns.NAME, player.getName());
        values.put(DBHelper.PlayersTable.Columns.METRIC, player.getMetric());
        if (player.getId() >= 0)
            db.update(DBHelper.PlayersTable.NAME, values,
                    DBHelper.PlayersTable.Columns._ID + "=" + player.getId(),
                    null);
        else
            db.insert(DBHelper.PlayersTable.NAME, null, values);
    }

    public void delete(Player player) {
        db.delete(DBHelper.PlayersTable.NAME, DBHelper.PlayersTable.Columns._ID
                + "=" + player.getId(), null);
    }

    public void deleteAllPlayers() {
        Player[] players = getAllPlayers();
        for (Player player : players)
            delete(player);
    }

    public void loadPlayersFromFile(File in) throws Exception {
        Scanner input = new Scanner(in);
        List<Player> players = new ArrayList<Player>();
        while (input.hasNext()) {
            String name = input.findInLine("(.*)\\t").trim();
            if (name == null)
                throw new Exception("Incorrect file format");
            double metric = input.nextDouble();
            players.add(new Player(-1, name, metric));
            input.nextLine();
        }
        boolean needOpenDB = !isOpened();
        if (needOpenDB)
            open();
        deleteAllPlayers();
        for (Player player : players)
            save(player);
        if (needOpenDB)
            close();
    }
}
