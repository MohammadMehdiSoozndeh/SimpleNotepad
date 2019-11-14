package com.example.nabztechandroidinternship2.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.nabztechandroidinternship2.EditNoteActivity;
import com.example.nabztechandroidinternship2.data.Note;
import com.example.nabztechandroidinternship2.database.NoteDatabase;
import com.example.nabztechandroidinternship2.R;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder> {

    private static final String DB_NAME = "note_db";
    private List<Note> dataset;
    private NoteDatabase db;

    public NoteRecyclerViewAdapter(List<Note> dataset) {
        this.dataset = dataset;
    }

    public void setData(List<Note> newData) {
        this.dataset = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteRecyclerViewAdapter.
            NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_recycler_item,
                parent,
                false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteRecyclerViewAdapter.NoteViewHolder holder,
                                 int position) {
        holder.title.setText(dataset.get(position).getTitle());
        holder.text.setText(dataset.get(position).getText());

        db = Room.databaseBuilder(holder.itemView.getContext(), NoteDatabase.class, DB_NAME).build();

        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(view.getContext(),
                    dataset.get(position).getTitle() + " Selected to edit",
                    Toast.LENGTH_SHORT).show();

            Completable.fromAction(new Action() {
                @Override
                public void run() throws Exception {
                    Intent intent = new Intent(holder.itemView.getContext(), EditNoteActivity.class);
                    intent.putExtra("note", dataset.get(position));
                    holder.itemView.getContext().startActivity(intent);
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onComplete() {
//                        setData(dataset);
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        });

        holder.itemView.setOnLongClickListener(view -> {

            Toast.makeText(view.getContext(),
                    dataset.get(position).getTitle() + " deleted",
                    Toast.LENGTH_SHORT).show();

            Completable.fromAction(() -> {
                db.noteDao().deleteNote(dataset.get(position));
                dataset.remove(position);
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onComplete() {
                    setData(dataset);
                }

                @Override
                public void onError(Throwable e) {

                }
            });

            return false;
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView text;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_item_title_id);
            text = itemView.findViewById(R.id.list_item_text_id);
        }
    }
}
