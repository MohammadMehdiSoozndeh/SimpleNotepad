package com.example.nabztechandroidinternship2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nabztechandroidinternship2.viewmodel.NoteViewModel;
import com.example.nabztechandroidinternship2.data.Note;

public class EditNoteActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText textEditText;

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        noteViewModel = new NoteViewModel(getApplication());

        setupViews();
    }

    private void setupViews() {
        titleEditText = findViewById(R.id.title_edit_text_id);
        textEditText = findViewById(R.id.text_edit_text_id);

        boolean isEditing = false;

        Intent intent = getIntent();
        Note noteEditing = (Note) intent.getSerializableExtra("note");

        if (noteEditing != null) {
            titleEditText.setText(noteEditing.getTitle());
            textEditText.setText(noteEditing.getText());
            isEditing = true;
        }

        Button saveBtn = findViewById(R.id.save_note_btn_id);
        boolean finalIsEditing = isEditing;

        saveBtn.setOnClickListener(view -> {
            if (!titleEditText.getText().toString().isEmpty()
                    && !textEditText.getText().toString().isEmpty()) {

                if (finalIsEditing) {
                    noteEditing.setTitle(titleEditText.getText().toString());
                    noteEditing.setText(textEditText.getText().toString());
                    noteViewModel.updateNote(noteEditing);
                    Toast.makeText(this, "Note Edited! :)", Toast.LENGTH_SHORT).show();
                } else {
                    Note note = new Note();
                    note.setTitle(titleEditText.getText().toString());
                    note.setText(textEditText.getText().toString());
                    noteViewModel.addNote(note);
                    Toast.makeText(this, "Note Created! :)", Toast.LENGTH_SHORT).show();
                }

                finish();
            } else {
                Toast.makeText(EditNoteActivity.this, "pls enter data :|", Toast.LENGTH_SHORT).show();
            }

        });


        Button delBtn = findViewById(R.id.del_note_btn_id);
        delBtn.setOnClickListener(view -> {
            if (noteEditing != null) {
                noteViewModel.deleteNote(noteEditing);
                Toast.makeText(EditNoteActivity.this, "note deleted!", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}
