package com.example.george.memento;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.george.memento.fragment.BooksEduFragment;
import com.example.george.memento.fragment.BooksFragment;
import com.example.george.memento.fragment.GamesFragment;
import com.example.george.memento.fragment.MoviesFragment;
import com.example.george.memento.fragment.ShowsFragment;

public class CreateItemActivity extends AppCompatActivity {

    private Spinner categorySpinner;

//    private Spinner yearSpinner;

    private EditText title;
    private EditText year;
    private EditText genre;
    private EditText description;
    private RatingBar rating;

    private String itemTitle;
    private String itemCategory;
    private String itemYear;
    private String itemGenre;
    private float itemRating;
    private String itemDescription;
    private int colorResource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        initCategorySpinner();

        this.title = (EditText) findViewById(R.id.title);
        this.year = (EditText) findViewById(R.id.year);
        this.genre = (EditText) findViewById(R.id.genre);
        this.description = (EditText) findViewById(R.id.description);
        this.rating = (RatingBar) findViewById(R.id.rating_bar);

        Button btnOk = (Button) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    private void initCategorySpinner() {
        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
    }

    private Item createItem() {
        this.itemTitle = title.getText().toString();
        this.itemCategory = categorySpinner.getSelectedItem().toString();
        this.itemYear = year.getText().toString();
        this.itemGenre = genre.getText().toString();
        this.itemRating = rating.getRating();
        this.itemDescription = description.getText().toString();

        switch (itemCategory) {
            case "Books":
                colorResource = R.color.category_books;
                break;
            case "Books(edu)":
                colorResource = R.color.category_books_edu;
                break;
            case "Movies":
                colorResource = R.color.category_movies;
                break;
            case "Shows":
                colorResource = R.color.category_shows;
                break;
            case "Games":
                colorResource = R.color.category_games;
                break;
        }

        return new Item(itemTitle, itemYear, itemGenre, itemCategory, itemRating, itemDescription, colorResource);
    }

    private void submit() {
        Item item = createItem();
        if (itemTitle.equals("")) {
            Toast.makeText(CreateItemActivity.this, "Title is required field", Toast.LENGTH_SHORT).show();
        } else {
            switch (itemCategory) {
                case "Books":
                    BooksFragment.addItem(0, item);
                    BooksFragment.writeList(CreateItemActivity.this);
                    break;
                case "Books(edu)":
                    BooksEduFragment.addItem(0, item);
                    BooksEduFragment.writeList(CreateItemActivity.this);
                    break;
                case "Movies":
                    MoviesFragment.addItem(0, item);
                    MoviesFragment.writeList(CreateItemActivity.this);
                    break;
                case "Shows":
                    ShowsFragment.addItem(0, item);
                    ShowsFragment.writeList(CreateItemActivity.this);
                    break;
                case "Games":
                    GamesFragment.addItem(0, item);
                    GamesFragment.writeList(CreateItemActivity.this);
                    break;
            }
            finish();
        }
    }
}
