package com.yifuyou.web.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WebDbHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "yifyou.db";

    public WebDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE loadFileRecord(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name VARCHAR(40)," +
                " path VARCHAR(60)," +
                " fromUrl VARCHAR(120)," +
                " state int," +
                " startTime Long," +
                " latestTime Long," +
                " loadLength Long," +
                " fileLength Long)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
