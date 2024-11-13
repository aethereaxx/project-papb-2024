package com.example.notedify_recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NotesAdapter.OnItemClickListener {

    private RecyclerView rvNotes;
    private NotesAdapter notesAdapter;
    private EditText etSearch;
    private ImageButton btnBack, btnSearch;
    private Button btnCategory, btnDates;
    private List<Notes> allNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rvNotes = this.findViewById(R.id.rvNotes);
        this.etSearch = this.findViewById(R.id.etSearch);
        this.btnBack = this.findViewById(R.id.btnBack);
        this.btnSearch = this.findViewById(R.id.btnSearch);
        this.btnCategory = this.findViewById(R.id.btnCategory);
        this.btnDates = this.findViewById(R.id.btnDates);

        this.btnBack.setOnClickListener(this);
        this.btnSearch.setOnClickListener(this);
        this.btnCategory.setOnClickListener(this);
        this.btnDates.setOnClickListener(this);

        this.allNotes = new ArrayList<>();
        this.allNotes.add(new Notes("The Who, What, Why of AI", "If AI is the answer, then what is the question?", "Interesting Idea", "01/02/2024"));
        this.allNotes.add(new Notes("New Product Idea Design", "Create a mobile app UI Kit that provide a basic notes functionality but with some improvement.", "Interesting Idea", "03/02/2024"));
        // Add more sample data as needed

        this.notesAdapter = new NotesAdapter(this, new ArrayList<>());
        this.notesAdapter.setOnItemClickListener(this);
        this.rvNotes.setAdapter(this.notesAdapter);
        this.rvNotes.setLayoutManager(new LinearLayoutManager(this));

        // Initially hide the RecyclerView
        this.rvNotes.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSearch) {
            performSearch();
        } else if (view.getId() == R.id.btnCategory) {
            Toast.makeText(this, "Category search mode selected", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.btnDates) {
            Toast.makeText(this, "Date search mode selected", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.btnBack) {
            // Clear search results and hide RecyclerView
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

        // Show the RecyclerView if there are search results
        if (!searchResults.isEmpty()) {
            this.rvNotes.setVisibility(View.VISIBLE);
        } else {
            this.rvNotes.setVisibility(View.GONE);
            Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(Notes note) {
        Toast.makeText(this, note.getTitle(), Toast.LENGTH_SHORT).show();
    }
}