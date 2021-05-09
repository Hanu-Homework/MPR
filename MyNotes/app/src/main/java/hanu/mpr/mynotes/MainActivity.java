package hanu.mpr.mynotes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.List;

import hanu.mpr.mynotes.adapters.NoteAdapter;
import hanu.mpr.mynotes.db.NoteManager;
import hanu.mpr.mynotes.models.Note;

public class MainActivity extends AppCompatActivity {
    public static final int NOTE_ADDED = 1;

    private List<Note> notes;
    private NoteAdapter noteAdapter;
    private NoteManager noteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dataset
        noteManager = NoteManager.getInstance(this);
        notes = noteManager.all();

        // adapter
        noteAdapter = new NoteAdapter(notes);

        // recycler view
        RecyclerView rvNotes = findViewById(R.id.rvNotes);
        rvNotes.setAdapter(noteAdapter);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));

        ImageButton btnAdd = findViewById(R.id.btnOK);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivityForResult(intent, NOTE_ADDED);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == NOTE_ADDED) {
            notes.clear();
            notes.addAll(noteManager.all());

            noteAdapter.notifyDataSetChanged();
        }
    }
}
