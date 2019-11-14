package com.example.nabztechandroidinternship2;

import android.content.Context;

import com.example.nabztechandroidinternship2.data.Note;
import com.example.nabztechandroidinternship2.database.NoteDao;
import com.example.nabztechandroidinternship2.database.NoteDatabase;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NoteRepository {

    private NoteDao noteDao;

    public NoteRepository(Context context) {
        this.noteDao = NoteDatabase.getInstance(context).noteDao();
    }

    public Flowable<List<Note>> getNoteList() {
        return noteDao.getNoteList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void addData(Note note) {
        noteDao.insertNote(note);
    }

    public void updateData(Note note) {
        noteDao.updateNote(note);
    }

    public void deleteData(Note note) {
        noteDao.deleteNote(note);
    }

}
