package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "journal.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableJournalSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertJournal(SQLiteDatabase db,String title,String content) {
        ContentValues values = new ContentValues();
        String pattern = "EEE, d MMM yyyy HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        values.put(DBTables.tableJournal.FIELD_TITLE,title);
        values.put(DBTables.tableJournal.FIELD_CONTENT,content);
        values.put(DBTables.tableJournal.FIELD_DATE_TIME,dateFormat.format(new Date()));

        return db.insert(DBTables.tableJournal.TABLE_NAME,null,values);
    }

    public long updateJournal(SQLiteDatabase db,String title,String content,String id) {
        String selection = DBTables.tableJournal.FIELD_ID+" = ?";
        String[] selectionArgs = {id};
        ContentValues values = new ContentValues();
        String pattern = "EEE, d MMM yyyy HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        values.put(DBTables.tableJournal.FIELD_TITLE,title);
        values.put(DBTables.tableJournal.FIELD_CONTENT,content);
        values.put(DBTables.tableJournal.FIELD_DATE_TIME,dateFormat.format(new Date()));

        return db.update(DBTables.tableJournal.TABLE_NAME,values,selection,selectionArgs);
    }

    public Cursor getAllJournals(SQLiteDatabase db) {

        String projection[] = {
                DBTables.tableJournal.FIELD_ID,
                DBTables.tableJournal.FIELD_TITLE,
                DBTables.tableJournal.FIELD_CONTENT,
                DBTables.tableJournal.FIELD_DATE_TIME
        };

        String orderBy = DBTables.tableJournal.FIELD_ID+" DESC";

        return db.query(DBTables.tableJournal.TABLE_NAME,projection,null,null,null,null,orderBy);

    }

    public Cursor getSingleJournal(SQLiteDatabase db, String id) {
        String projection[] = {
                DBTables.tableJournal.FIELD_ID,
                DBTables.tableJournal.FIELD_TITLE,
                DBTables.tableJournal.FIELD_CONTENT,
                DBTables.tableJournal.FIELD_DATE_TIME
        };

        String selection = DBTables.tableJournal.FIELD_ID+" = ?";
        String[] selectionArgs = {id};
        String orderBy = DBTables.tableJournal.FIELD_ID+" DESC";
        return db.query(DBTables.tableJournal.TABLE_NAME,projection,selection,selectionArgs,null,null,orderBy);
    }

    public int deleteJournal(SQLiteDatabase db, String id) {
        String selection = DBTables.tableJournal.FIELD_ID+" = ?";
        String[] selectionArgs = {id};
        return db.delete(DBTables.tableJournal.TABLE_NAME,selection,selectionArgs);

    }

    public String createTableJournalSQL() {
        String sql = "CREATE TABLE IF NOT EXISTS "+DBTables.tableJournal.TABLE_NAME+"(" +
                DBTables.tableJournal.FIELD_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                DBTables.tableJournal.FIELD_TITLE+" TEXT NOT NULL," +
                DBTables.tableJournal.FIELD_DATE_TIME+" TEXT NOT NULL," +
                DBTables.tableJournal.FIELD_CONTENT+" TEXT NOT NULL" +
                ")";
        return sql;
    }
}
