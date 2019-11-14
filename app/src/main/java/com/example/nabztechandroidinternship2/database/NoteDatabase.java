package com.example.nabztechandroidinternship2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.nabztechandroidinternship2.data.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static final String DB_NAME = "note_db";

    private static NoteDatabase instance;

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public abstract NoteDao noteDao();

}
