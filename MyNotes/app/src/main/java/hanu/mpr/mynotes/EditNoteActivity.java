package hanu.mpr.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hanu.mpr.mynotes.db.NoteManager;
import hanu.mpr.mynotes.models.Note;

public class EditNoteActivity extends AppCompatActivity {

    private EditText edtText;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        edtText = findViewById(R.id.edtText);

        // Init
        Intent intent = getIntent();
        Long id = intent.getLongExtra("NOTE_ID", 0);
        String text = intent.getStringExtra("NOTE_TEXT");

        note = new Note(id, text);

        edtText.setText(note.getText());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOK:
                String text = edtText.getText().toString();

                if (text.equals("")) {
                    Toast.makeText(this, "Missing entry", Toast.LENGTH_SHORT).show();
                } else {
                    NoteManager noteManager = NoteManager.getInstance(this);

                    noteManager.update(note);
                    Toast.makeText(this, "Updated Succcessfully", Toast.LENGTH_SHORT).show();

                    setResult(RESULT_OK);
                    finish();
                }
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }
}
