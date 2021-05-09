package hanu.mpr.mynotes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import hanu.mpr.mynotes.EditNoteActivity;
import hanu.mpr.mynotes.R;
import hanu.mpr.mynotes.db.NoteManager;
import hanu.mpr.mynotes.models.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private final List<Note> notes;
    private Context context;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_note, parent, false);

        return new NoteHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, final int position) {
        Note friend = this.notes.get(position);

        holder.bind(friend);
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final ImageButton btnDelete;
        private final ImageButton btnEdit;

        private final Context mContext;

        public NoteHolder(@NonNull View itemView, final Context context) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);

            mContext = context;
        }

        public void bind(final Note note) {
            tvName.setText(note.getText());

            // edit item
            btnEdit.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, EditNoteActivity.class);
                intent.putExtra("NOTE_ID", note.getId());
                intent.putExtra("NOTE_TEXT", note.getText());
                mContext.startActivity(intent);
            });

            // delete item
            btnDelete.setOnClickListener(v -> {
                NoteManager friendManager = NoteManager.getInstance(context);
                friendManager.delete(note.getId());

                notes.remove(note);
                notifyDataSetChanged();
            });
        }
    }
}
