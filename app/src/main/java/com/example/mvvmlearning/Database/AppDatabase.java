package com.example.mvvmlearning.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvmlearning.ProjectModel;

@Database(entities = {ProjectModel.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "project_database.db";
    public static AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract ProjectDao projectDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }

        return instance;
    }

}
