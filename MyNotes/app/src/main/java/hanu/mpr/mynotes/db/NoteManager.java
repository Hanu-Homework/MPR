package hanu.mpr.mynotes.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

import hanu.mpr.mynotes.models.Note;


public class NoteManager {
    // singleton
    private static NoteManager instance;

    private static final String INSERT_STMT =
            "INSERT INTO " + DbSchema.NotesTable.NAME + "(text) VALUES (?)";

    private static final String UPDATE_STMT = "UPDATE notes " + "SET text = ?" + "WHERE id = ?";

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public static NoteManager getInstance(Context context) {
        if (instance == null) {
            instance = new NoteManager(context);
        }

        return instance;
    }

    private NoteManager(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Note> all() {
        String sql = "SELECT * FROM notes";
        Cursor cursor = db.rawQuery(sql, null);
        NoteCursorWrapper cursorWrapper = new NoteCursorWrapper(cursor);

        return cursorWrapper.getNotes();
    }

    /**
     * @modifies note
     */
    public boolean add(Note note) {
        SQLiteStatement statement = db.compileStatement(INSERT_STMT);

        statement.bindString(1, note.getText());

        long id = statement.executeInsert();

//        statement.executeUpdateDelete();
        // a
        if (id > 0) {
            note.setId(id);
            return true;
        }

        return false;
    }

    public boolean update(Note note) {
        SQLiteStatement statement = db.compileStatement(UPDATE_STMT);
        statement.bindString(1, note.getText());
        statement.bindLong(2, note.getId());
        int affectedCount = statement.executeUpdateDelete();
        return affectedCount > 0;
    }

    public boolean delete(long id) {
        int result = db.delete(DbSchema.NotesTable.NAME, "id = ?", new String[] { id + "" });

        return result > 0;
    }
}