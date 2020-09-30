package com.example.taskmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.taskmanager.database.TaskDBSchema.TaskTable.Cols;

public class TaskDBHelper extends SQLiteOpenHelper {
    public TaskDBHelper(@Nullable Context context) {
        super(context, TaskDBSchema.NAME, null, TaskDBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("CREATE TABLE" + TaskDBSchema.TaskTable.NAME + " (");
        sbQuery.append(Cols.ID);
        sbQuery.append(Cols.UUID);
        sbQuery.append(Cols.TITLE);
        sbQuery.append(Cols.DESCRIPTION);
        sbQuery.append(Cols.DATE);
        sbQuery.append(Cols.TIME);
        sbQuery.append(Cols.STATE);
        sbQuery.append(");");
        sqLiteDatabase.execSQL(sbQuery.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
