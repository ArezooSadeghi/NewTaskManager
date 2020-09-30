package com.example.taskmanager.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.taskmanager.database.TaskDBHelper;
import com.example.taskmanager.database.TaskDBSchema;
import com.example.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.example.taskmanager.database.TaskDBSchema.TaskTable.Cols;

public class TaskDBRepository implements IRepository {
    private static TaskDBRepository sInstance;
    private SQLiteDatabase mDatabase;

    public TaskDBRepository(Context context) {
        TaskDBHelper taskDBHelper = new TaskDBHelper(context);
        mDatabase = taskDBHelper.getWritableDatabase();
    }

    public static TaskDBRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new TaskDBRepository(context);
        }
        return sInstance;
    }

    @Override
    public List<Task> getCrimes() {
        List<Task> tasks = new ArrayList<>();
        Cursor cursor = mDatabase.query(TaskDBSchema.TaskTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        if (cursor == null || cursor.getCount() == 0) {
            return tasks;
        }
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Task task = new Task();
                UUID uuid = UUID.fromString(cursor.getString(cursor.getColumnIndex(Cols.UUID)));
                String title = cursor.getString(cursor.getColumnIndex(Cols.TITLE));
                String description = cursor.getString(cursor.getColumnIndex(Cols.DESCRIPTION));
                Date date = new Date(cursor.getLong(cursor.getColumnIndex(Cols.DATE)));
                task.setTitle(title);
                task.setDescription(description);
                task.setDate(date);
            }
        } finally {
            cursor.close();
        }
        return tasks;
    }


    @Override
    public void insert(Task task) {
        ContentValues values = getContentValues(task);
        mDatabase.insert(TaskDBSchema.TaskTable.NAME, null, values);

    }

    public ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(Cols.UUID, task.getId().toString());
        values.put(Cols.TITLE, task.getTitle());
        values.put(Cols.DATE, task.getDate().getTime());
        values.put(Cols.DESCRIPTION, task.getDescription());
        return values;
    }


    @Override
    public Task getCrime(UUID taskId) {
        return null;
    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(Task task) {

    }

    @Override
    public int getPosition(UUID taskId) {
        return 0;
    }
}
