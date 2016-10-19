package com.example.george.memento.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.george.memento.R;
import com.example.george.memento.fragment.BooksEduFragment;
import com.example.george.memento.fragment.BooksFragment;
import com.example.george.memento.fragment.GamesFragment;
import com.example.george.memento.fragment.MoviesFragment;
import com.example.george.memento.fragment.ShowsFragment;

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context context;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BooksFragment();
        } else if (position == 1) {
            return new BooksEduFragment();
        } else if (position == 2) {
            return new MoviesFragment();
        } else if (position == 3) {
            return new ShowsFragment();
        } else {
            return new GamesFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.category_books);
        } else if (position == 1) {
            return context.getString(R.string.category_books_edu);
        } else if (position == 2) {
            return context.getString(R.string.category_movies);
        } else if (position == 3) {
            return context.getString(R.string.category_shows);
        } else {
            return context.getString(R.string.category_games);
        }
    }
}
