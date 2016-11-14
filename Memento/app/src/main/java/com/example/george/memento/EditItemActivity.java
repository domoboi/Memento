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
import com.example.george.memento.fragment.MusicFragment;
import com.example.george.memento.fragment.ShowsFragment;


public class EditItemActivity extends AppCompatActivity {

    private final static int BOOKS_CATEGORY_POSITION = 0;
    private final static int BOOKS_EDU_CATEGORY_POSITION = 1;
    private final static int MOVIES_CATEGORY_POSITION = 2;
    private final static int SHOWS_CATEGORY_POSITION = 3;
    private final static int GAMES_CATEGORY_POSITION = 4;
    private final static int MUSIC_CATEGORY_POSITION = 5;
    private Spinner categorySpinner;
    private EditText year;
    private EditText genre;
    private EditText title;
    private EditText description;
    private RatingBar rating;
    private String itemTitle;
    private String itemCategory;
    private String itemYear;
    private String itemGenre;
    private float itemRating;
    private String itemDescription;
    private int colorResource;
    private Item item;

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
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.item = (Item) extras.getSerializable("item");
            getDataFromItem(item);
        }
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

    private void getDataFromItem(Item item) {
        this.itemTitle = item.getTitle();
        this.itemYear = item.getYear();
        this.itemGenre = item.getGenre();
        this.itemCategory = item.getCategory();
        this.itemDescription = item.getDescription();
        this.itemRating = item.getRating();
        switch (itemCategory) {
            case "Books":
                categorySpinner.setSelection(BOOKS_CATEGORY_POSITION);
                break;
            case "Books(edu)":
                categorySpinner.setSelection(BOOKS_EDU_CATEGORY_POSITION);
                break;
            case "Movies":
                categorySpinner.setSelection(MOVIES_CATEGORY_POSITION);
                break;
            case "Shows":
                categorySpinner.setSelection(SHOWS_CATEGORY_POSITION);
                break;
            case "Games":
                categorySpinner.setSelection(GAMES_CATEGORY_POSITION);
                break;
            case "Music":
                categorySpinner.setSelection(MUSIC_CATEGORY_POSITION);
                break;
        }

        this.title.setText(itemTitle);
        this.year.setText(itemYear);
        this.genre.setText(itemGenre);
        this.description.setText(itemDescription);
        this.rating.setRating(itemRating);
    }

    private void createItem() {
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
            case "Music":
                colorResource = R.color.category_music;
                break;
        }

        item.setTitle(itemTitle);
        item.setYear(itemYear);
        item.setGenre(itemGenre);
        item.setCategory(itemCategory);
        item.setRating(itemRating);
        item.setDescription(itemDescription);
        item.setColorResource(colorResource);
    }

    private void submit() {
        switch (itemCategory) {
            case "Books":
                BooksFragment.removeItem(item);
                BooksFragment.writeList(EditItemActivity.this);
                break;
            case "Books(edu)":
                BooksEduFragment.removeItem(item);
                BooksEduFragment.writeList(EditItemActivity.this);
                break;
            case "Movies":
                MoviesFragment.removeItem(item);
                MoviesFragment.writeList(EditItemActivity.this);
                break;
            case "Shows":
                ShowsFragment.removeItem(item);
                ShowsFragment.writeList(EditItemActivity.this);
                break;
            case "Games":
                GamesFragment.removeItem(item);
                GamesFragment.writeList(EditItemActivity.this);
                break;
            case "Music":
                MusicFragment.removeItem(item);
                MusicFragment.writeList(EditItemActivity.this);
                break;
        }
        createItem();
        if (itemTitle.equals("")) {
            Toast.makeText(EditItemActivity.this, EditItemActivity.this.getResources().getString(R.string.title_required), Toast.LENGTH_SHORT).show();
        } else {
            switch (itemCategory) {
                case "Books":
                    BooksFragment.addItem(0, item);
                    BooksFragment.writeList(EditItemActivity.this);
                    break;
                case "Books(edu)":
                    BooksEduFragment.addItem(0, item);
                    BooksEduFragment.writeList(EditItemActivity.this);
                    break;
                case "Movies":
                    MoviesFragment.addItem(0, item);
                    MoviesFragment.writeList(EditItemActivity.this);
                    break;
                case "Shows":
                    ShowsFragment.addItem(0, item);
                    ShowsFragment.writeList(EditItemActivity.this);
                    break;
                case "Games":
                    GamesFragment.addItem(0, item);
                    GamesFragment.writeList(EditItemActivity.this);
                    break;
                case "Music":
                    MusicFragment.addItem(0, item);
                    MusicFragment.writeList(EditItemActivity.this);
                    break;
            }
            finish();
        }
    }
}
