package com.example.nabztechandroidinternship2.viewmodel;

import android.content.Context;

import com.example.nabztechandroidinternship2.NoteRepository;
import com.example.nabztechandroidinternship2.data.Note;

import java.util.List;

import io.reactivex.Flowable;

public class MainViewModel {

    private NoteRepository noteRepository;

    public MainViewModel(Context context) {
        this.noteRepository = new NoteRepository(context);
    }

    public Flowable<List<Note>> getNoteList() {
        return noteRepository.getNoteList();
    }
}
