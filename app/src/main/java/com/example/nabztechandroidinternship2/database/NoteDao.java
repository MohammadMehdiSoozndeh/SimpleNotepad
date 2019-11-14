package com.example.nabztechandroidinternship2.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nabztechandroidinternship2.data.Note;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NoteDao {

    @Query("Select * from notebook")
    Flowable<List<Note>> getNoteList();

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

}
