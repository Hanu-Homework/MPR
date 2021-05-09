package hanu.mpr.mynotes.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.List;

import hanu.mpr.mynotes.models.Note;


public class NoteCursorWrapper extends CursorWrapper {
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote() {
        Long id = getLong(getColumnIndex(DbSchema.NotesTable.Cols.ID));

        String text = getString(getColumnIndex(DbSchema.NotesTable.Cols.TEXT));

        return new Note(id, text);
    }

    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();

        moveToFirst();
        while (!isAfterLast()) {
            Note note = getNote();
            notes.add(note);

            moveToNext();
        }

        return notes;
    }
}
