package com.example.george.memento;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.george.memento.adapter.ItemAdapter;
import com.example.george.memento.fragment.BooksEduFragment;
import com.example.george.memento.fragment.BooksFragment;
import com.example.george.memento.fragment.GamesFragment;
import com.example.george.memento.fragment.MoviesFragment;
import com.example.george.memento.fragment.MusicFragment;
import com.example.george.memento.fragment.ShowsFragment;

import java.util.ArrayList;

public class SearchableActivity extends AppCompatActivity{

    private static ArrayList<Item> items;
    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static String query;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(SearchableActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(SearchableActivity.this);
        }
    }

    public static void doSearch(Context context) {
        items = null;
        adapter = null;

        items = new ArrayList<Item>();
        query = query.toLowerCase();

        String title;
        String genre;
        String year;

        for (int i = 0; i < BooksFragment.getSize(); i++) {
            title = BooksFragment.getItem(i).getTitle().toLowerCase();
            genre = BooksFragment.getItem(i).getGenre().toLowerCase();
            year = BooksFragment.getItem(i).getYear().toLowerCase();
            if (title.contains(query) || genre.contains(query) || year.equals(query)) {
                SearchableActivity.items.add(BooksFragment.getItem(i));
            }
        }

        for (int i = 0; i < BooksEduFragment.getSize(); i++) {
            title = BooksEduFragment.getItem(i).getTitle().toLowerCase();
            genre = BooksEduFragment.getItem(i).getGenre().toLowerCase();
            year = BooksEduFragment.getItem(i).getYear().toLowerCase();
            if (title.contains(query) || genre.contains(query) || year.equals(query)) {
                SearchableActivity.items.add(BooksEduFragment.getItem(i));
            }
        }

        for (int i = 0; i < MoviesFragment.getSize(); i++) {
            title = MoviesFragment.getItem(i).getTitle().toLowerCase();
            genre = MoviesFragment.getItem(i).getGenre().toLowerCase();
            year = MoviesFragment.getItem(i).getYear().toLowerCase();
            if (title.contains(query) || genre.contains(query) || year.equals(query)) {
                SearchableActivity.items.add(MoviesFragment.getItem(i));
            }
        }

        for (int i = 0; i < ShowsFragment.getSize(); i++) {
            title = ShowsFragment.getItem(i).getTitle().toLowerCase();
            genre = ShowsFragment.getItem(i).getGenre().toLowerCase();
            year = ShowsFragment.getItem(i).getYear().toLowerCase();
            if (title.contains(query) || genre.contains(query) || year.equals(query)) {
                SearchableActivity.items.add(ShowsFragment.getItem(i));
            }
        }

        for (int i = 0; i < GamesFragment.getSize(); i++) {
            title = GamesFragment.getItem(i).getTitle().toLowerCase();
            genre = GamesFragment.getItem(i).getGenre().toLowerCase();
            year = GamesFragment.getItem(i).getYear().toLowerCase();
            if (title.contains(query) || genre.contains(query) || year.equals(query)) {
                SearchableActivity.items.add(GamesFragment.getItem(i));
            }
        }

        for (int i = 0; i < MusicFragment.getSize(); i++) {
            title = MusicFragment.getItem(i).getTitle().toLowerCase();
            genre = MusicFragment.getItem(i).getGenre().toLowerCase();
            year = MusicFragment.getItem(i).getYear().toLowerCase();
            if (title.contains(query) || genre.contains(query) || year.equals(query)) {
                SearchableActivity.items.add(MusicFragment.getItem(i));
            }
        }

        adapter = new ItemAdapter(context, items);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (items.size() == 0) {
            Toast.makeText(context, context.getResources().getString(R.string.empty_search), Toast.LENGTH_LONG).show();
        } else if (items.size() == 1) {
            Toast.makeText(context, context.getResources().getString(R.string.one_item_search), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, items.size() + " " + context.getResources().getString(R.string.number_items_search), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        doSearch(SearchableActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        query = null;
        items = null;
        adapter = null;
        recyclerView = null;
    }
}
