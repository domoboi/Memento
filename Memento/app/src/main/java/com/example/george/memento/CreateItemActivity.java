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

public class CreateItemActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        initCategorySpinner();
        initYearSpinner();

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

    private Item createItem() {
        this.title = (EditText) findViewById(R.id.title);
        this.description = (EditText) findViewById(R.id.description);
        this.rating = (RatingBar) findViewById(R.id.rating_bar);

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
            Toast.makeText(CreateItemActivity.this, "Title is required field", Toast.LENGTH_SHORT).show();
        } else if (itemCategory.equals("")) {
            Toast.makeText(CreateItemActivity.this, "Category is required field", Toast.LENGTH_SHORT).show();
        } else {
            switch (itemCategory) {
                case "Books":
                    BooksFragment.addItem(0, item);
                    BooksFragment.writeList(getApplicationContext());
                    break;
                case "Books(edu)":
                    BooksEduFragment.addItem(0, item);
                    BooksEduFragment.writeList(getApplicationContext());
                    break;
                case "Movies":
                    MoviesFragment.addItem(0, item);
                    MoviesFragment.writeList(getApplicationContext());
                    break;
                case "Shows":
                    ShowsFragment.addItem(0, item);
                    ShowsFragment.writeList(getApplicationContext());
                    break;
                case "Games":
                    GamesFragment.addItem(0, item);
                    GamesFragment.writeList(getApplicationContext());
                    break;
            }
            finish();
        }
    }
}
