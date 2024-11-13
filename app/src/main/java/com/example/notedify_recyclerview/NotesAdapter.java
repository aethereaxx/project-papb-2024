package com.example.notedify_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesVH> {

    private final Context ctx;
    private List<Notes> data;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Notes note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public NotesAdapter(Context ctx, List<Notes> data) {
        this.ctx = ctx;
        this.data = data;
    }

    public class NotesVH extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvContent;
        private final TextView tvCategory;
        private final TextView tvDate;

        public NotesVH(@NonNull View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvContent = itemView.findViewById(R.id.tvContent);
            this.tvCategory = itemView.findViewById(R.id.tvCategory);
            this.tvDate = itemView.findViewById(R.id.tvDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(data.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public NotesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(this.ctx)
                .inflate(R.layout.item_note, parent, false);
        return new NotesVH(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesVH holder, int position) {
        Notes note = this.data.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());
        holder.tvCategory.setText(note.getCategory());
        holder.tvDate.setText(note.getDate());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public void setNotes(List<Notes> notes) {
        this.data = notes;
    }
}