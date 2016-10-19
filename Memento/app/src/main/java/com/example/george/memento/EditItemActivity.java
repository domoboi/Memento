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

import java.util.ArrayList;
import java.util.Calendar;


public class EditItemActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    private Spinner yearSpinner;

    private EditText title;
    private EditText description;
    private RatingBar rating;

    private String itemTitle;
    private String itemCategory;
    private String itemYear;
    private float itemRating;
    private String itemDescription;

    private int position;

    private final static int BOOKS_CATEGORY = 0;
    private final static int BOOKS_EDU_CATEGORY = 1;
    private final static int MOVIES_CATEGORY = 2;
    private final static int SHOWS_CATEGORY = 3;
    private final static int GAMES_CATEGORY = 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        initCategorySpinner();
        initYearSpinner();

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        rating = (RatingBar) findViewById(R.id.rating_bar);

        Button btnOk = (Button) findViewById(R.id.btn_ok);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.itemCategory = extras.getString("category");
            this.position = extras.getInt("position");
            getItem();
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

    private void initYearSpinner() {
        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear; i >= 1888; i--) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearSpinner = (Spinner) findViewById(R.id.year_spinner);
        yearSpinner.setAdapter(adapter);
    }

    private void getItem() {
        Item item;
        switch (itemCategory) {
            case "Books":
                item = BooksFragment.getItem(position);
                getDataFromItem(item, BOOKS_CATEGORY);
                BooksFragment.removeItem(position);
                BooksFragment.writeList(getApplicationContext());
                break;
            case "Books(edu)":
                item = BooksEduFragment.getItem(position);
                getDataFromItem(item, BOOKS_EDU_CATEGORY);
                BooksEduFragment.removeItem(position);
                BooksEduFragment.writeList(getApplicationContext());
                break;
            case "Movies":
                item = MoviesFragment.getItem(position);
                getDataFromItem(item, MOVIES_CATEGORY);
                MoviesFragment.removeItem(position);
                MoviesFragment.writeList(getApplicationContext());
                break;
            case "Shows":
                item = ShowsFragment.getItem(position);
                getDataFromItem(item, SHOWS_CATEGORY);
                ShowsFragment.removeItem(position);
                ShowsFragment.writeList(getApplicationContext());
                break;
            case "Games":
                item = GamesFragment.getItem(position);
                getDataFromItem(item, GAMES_CATEGORY);
                GamesFragment.removeItem(position);
                GamesFragment.writeList(getApplicationContext());
                break;
        }
    }

    private void getDataFromItem(Item item, int category) {
        int year;
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        int yearIndex;

        this.itemTitle = item.getTitle();
        this.itemDescription = item.getDescription();
        this.itemRating = item.getRating();
        categorySpinner.setSelection(category);
        itemYear = item.getYear();
        if (this.itemYear != null &&  !("".equals(this.itemYear))) {
            year = Integer.valueOf(itemYear);
            yearIndex = thisYear - year;
            yearSpinner.setSelection(yearIndex);
        } else {
            yearSpinner.setSelection(0);
        }

        this.title.setText(itemTitle);
        this.description.setText(itemDescription);
        this.rating.setRating(itemRating);
    }

    private Item createItem() {
        this.itemTitle = title.getText().toString();
        this.itemCategory = categorySpinner.getSelectedItem().toString();
        this.itemYear = yearSpinner.getSelectedItem().toString();
        this.itemRating = rating.getRating();
        this.itemDescription = description.getText().toString();

        return new Item(itemTitle, itemYear, itemCategory, itemRating, itemDescription);
    }

    private void submit() {
        Item item = createItem();
        if (itemTitle.equals("")) {
            Toast.makeText(EditItemActivity.this, "Title is required field", Toast.LENGTH_SHORT).show();
        } else if (itemCategory.equals("")) {
            Toast.makeText(EditItemActivity.this, "Category is required field", Toast.LENGTH_SHORT).show();
        } else if (itemDescription.equals("")) {
            Toast.makeText(EditItemActivity.this, "Description is required field", Toast.LENGTH_SHORT).show();
        } else {
            switch (itemCategory) {
                case "Books":
                    BooksFragment.addItem(position, item);
                    BooksFragment.writeList(getApplicationContext());
                    break;
                case "Books(edu)":
                    BooksEduFragment.addItem(position, item);
                    BooksEduFragment.writeList(getApplicationContext());
                    break;
                case "Movies":
                    MoviesFragment.addItem(position, item);
                    MoviesFragment.writeList(getApplicationContext());
                    break;
                case "Shows":
                    ShowsFragment.addItem(position, item);
                    ShowsFragment.writeList(getApplicationContext());
                    break;
                case "Games":
                    GamesFragment.addItem(position, item);
                    GamesFragment.writeList(getApplicationContext());
                    break;
            }
            finish();
        }
    }
}
