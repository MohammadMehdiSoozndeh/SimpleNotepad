package com.example.nabztechandroidinternship2.viewmodel;

import android.content.Context;

import com.example.nabztechandroidinternship2.NoteRepository;
import com.example.nabztechandroidinternship2.data.Note;

public class NoteViewModel {

    private NoteRepository noteRepository;

    public NoteViewModel(Context context) {
        noteRepository = new NoteRepository(context);
    }

    public void addNote(Note note) {
        noteRepository.addData(note);
    }

    public void updateNote(Note note) {
        noteRepository.updateData(note);
    }

    public void deleteNote(Note note) {
        noteRepository.deleteData(note);
    }

}
