package com.example.notedify_recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment implements View.OnClickListener, NotesAdapter.OnItemClickListener {

    private RecyclerView rvNotes;
    private NotesAdapter notesAdapter;
    private EditText etSearch;
    private ImageButton btnBack, btnSearch;
    private Button btnCategory, btnDates;
    private List<Notes> allNotes;

    public NotesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notes, container, false);

        // Initialize views
        this.rvNotes = v.findViewById(R.id.rvNotes);
        this.etSearch = v.findViewById(R.id.etSearch);
        this.btnBack = v.findViewById(R.id.btnBack);
        this.btnSearch = v.findViewById(R.id.btnSearch);
        this.btnCategory = v.findViewById(R.id.btnCategory);
        this.btnDates = v.findViewById(R.id.btnDates);

        // Set click listeners
        this.btnBack.setOnClickListener(this);
        this.btnSearch.setOnClickListener(this);
        this.btnCategory.setOnClickListener(this);
        this.btnDates.setOnClickListener(this);

        // Setup RecyclerView
        this.allNotes = new ArrayList<>();
        this.allNotes.add(new Notes("The Who, What, Why of AI", "If AI is the answer, then what is the question?", "Interesting Idea", "01/02/2024"));
        this.allNotes.add(new Notes("New Product Idea Design", "Create a mobile app UI Kit that provide a basic notes functionality but with some improvement.", "Interesting Idea", "03/02/2024"));

        this.notesAdapter = new NotesAdapter(getContext(), new ArrayList<>());
        this.notesAdapter.setOnItemClickListener(this);
        this.rvNotes.setAdapter(this.notesAdapter);
        this.rvNotes.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rvNotes.setVisibility(View.GONE);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSearch) {
            performSearch();
        } else if (view.getId() == R.id.btnCategory) {
            Toast.makeText(getContext(), "Category search mode selected", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.btnDates) {
            Toast.makeText(getContext(), "Date search mode selected", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.btnBack) {
            this.etSearch.setText("");
            this.rvNotes.setVisibility(View.GONE);
        }
    }

    private void performSearch() {
        String query = etSearch.getText().toString().trim();
        List<Notes> searchResults = new ArrayList<>();

        for (Notes note : this.allNotes) {
            if (note.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    note.getContent().toLowerCase().contains(query.toLowerCase()) ||
                    note.getCategory().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(note);
            }
        }

        this.notesAdapter.setNotes(searchResults);
        this.notesAdapter.notifyDataSetChanged();

        if (!searchResults.isEmpty()) {
            this.rvNotes.setVisibility(View.VISIBLE);
        } else {
            this.rvNotes.setVisibility(View.GONE);
            Toast.makeText(getContext(), "No results found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(Notes note) {
        Toast.makeText(getContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
    }
}