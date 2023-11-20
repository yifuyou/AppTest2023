package com.yifuyou.web.load.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.yifuyou.web.load.CommandUtil;
import com.yifuyou.web.load.LoadFileRecord;

import java.util.ArrayList;
import java.util.List;

public class DataBaseUtil {
    public static final String TAG = "DataBaseUtil";

    private static final String FILE_TABLE_NAME = "loadFileRecord";

    private static WebDbHelper mDbHelper;

    public static void dataInit(Context context) {
        if (context == null) {
            Log.e(TAG, "dataInit: context null!" );
            return;
        }
        mDbHelper = new WebDbHelper(context);
    }


    public static void insertLoadFileRecord(LoadFileRecord loadFileRecord){
        ContentValues values = new ContentValues();
        values.put("name", loadFileRecord.getName());
        values.put("path", loadFileRecord.getPath());
        values.put("fromUrl", loadFileRecord.getUrl());
        values.put("state", CommandUtil.getStateByString(loadFileRecord.getState()));
        values.put("startTime", loadFileRecord.getsTime());
        values.put("latestTime", loadFileRecord.getlTime());
        mDbHelper.getWritableDatabase().insert(FILE_TABLE_NAME, null, values);
    }

    public static void updateLoadFileRecordTime(String fName, int state, long lTime) {
        ContentValues values = new ContentValues();
        values.put("latestTime", lTime);
        values.put("state", state);
        mDbHelper.getWritableDatabase().update(FILE_TABLE_NAME, values, "name=?", new String[]{fName});
    }


    public static List<LoadFileRecord> getAllLoadFileRecord() {
        Cursor cursor = mDbHelper.getReadableDatabase().query(FILE_TABLE_NAME, null, null, null, null, null, null);
        if (!cursor.moveToFirst()) {
            return new ArrayList<>(0);
        }

        ArrayList<LoadFileRecord> loadFileRecords = new ArrayList<>();
        do {
            LoadFileRecord loadFileRecord = new LoadFileRecord();
            loadFileRecord.setName(cursor.getString(1));
            loadFileRecord.setPath(cursor.getString(2));
            loadFileRecord.setUrl(cursor.getString(3));
            loadFileRecord.setState(CommandUtil.getStateByInt(cursor.getInt(4)));
            loadFileRecord.setsTime(cursor.getLong(5));
            loadFileRecord.setlTime(cursor.getLong(6));
            loadFileRecords.add(loadFileRecord);
        } while (cursor.moveToNext());
        cursor.close();
        return loadFileRecords;
    }

}
