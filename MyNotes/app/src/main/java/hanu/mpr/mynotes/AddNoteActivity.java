package hanu.mpr.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hanu.mpr.mynotes.db.NoteManager;
import hanu.mpr.mynotes.models.Note;


public class AddNoteActivity extends AppCompatActivity {

    private EditText edtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edtText = findViewById(R.id.edtText);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOK:
                String text = edtText.getText().toString();

                if (text.equals("")) {
                    Toast.makeText(this, "Missing entry", Toast.LENGTH_SHORT).show();
                } else {
                    NoteManager noteManager = NoteManager.getInstance(this);

                    Note note = new Note(text);
                    noteManager.add(note);
                    Toast.makeText(this, "New record inserted", Toast.LENGTH_SHORT).show();

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
