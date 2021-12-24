package com.tugas.obatkeluarga;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "obat.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "pasien";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama_pasien";
    private static final String COLUMN_UMUR = "umur_pasien";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_PENANGANAN = "penanganan";
    private static final String COLUMN_KELUHAN = "keluhan";

    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAMA + " TEXT, " +
                        COLUMN_UMUR + " TEXT," +
                        COLUMN_GENDER + " TEXT, " +
                        COLUMN_KELUHAN + " TEXT, " +
                        COLUMN_PENANGANAN + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addpasien(String nama, String umur, String gender, String keluhan, String penanganan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAMA, nama);
        cv.put(COLUMN_UMUR, umur);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_KELUHAN, keluhan);
        cv.put(COLUMN_PENANGANAN, penanganan);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor getdata(String id) {
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " =?";

        Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(id)});
        return cursor;
    }


    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String rowid, String nama, String umur, String gender, String keluhan, String penanganan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAMA, nama);
        cv.put(COLUMN_UMUR, umur);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_KELUHAN, keluhan);
        cv.put(COLUMN_PENANGANAN, penanganan);

        long result = db.update(TABLE_NAME,cv,"id=?", new String[]{rowid});
        if (result == -1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update Success " + nama, Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Delete Success ", Toast.LENGTH_SHORT).show();
        }
    }

    public void addpasien() {
    }
}
