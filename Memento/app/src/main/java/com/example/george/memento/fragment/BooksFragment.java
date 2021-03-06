package com.example.george.memento.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.george.memento.Item;
import com.example.george.memento.R;
import com.example.george.memento.adapter.ItemAdapter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BooksFragment extends Fragment {

    private static final String CATEGORY = "Books";

    private static final int colorResource = R.color.category_books;

    private static ArrayList<Item> items;
    private static RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public BooksFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_list, container, false);
        items = new ArrayList<Item>();
        items.add(new Item("Deadpool", "2016", "Action", CATEGORY, 1f, "Great!", colorResource));
        items.add(new Item("Deadpool", "2016", "Action", CATEGORY, 2f, "Great!", colorResource));
        items.add(new Item("Deadpool", "2016", "Action", CATEGORY, 3f, "Great!", colorResource));
        items.add(new Item("Deadpool", "2016", "Action", CATEGORY, 4f, "Great!", colorResource));
        items.add(new Item("Deadpool", "2016", "Action", CATEGORY, 5f, "Great!", colorResource));

        readList(getContext());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.list);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ItemAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return rootView;
    }

    public static void addItem(int position, Item item) {
        items.add(position, item);
        adapter.notifyDataSetChanged();
    }

    public static void removeItem(Item item) {
        items.remove(item);
        adapter.notifyDataSetChanged();
    }

    public static Item getItem(int position) {
        return items.get(position);
    }

    public static int getSize() {
        return items.size();
    }

    public static void writeList(Context c) {
        try {
            FileOutputStream fos = c.openFileOutput(CATEGORY + ".dat", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void readList(Context c) {
        try {
            FileInputStream fis = c.openFileInput(CATEGORY + ".dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            items = (ArrayList<Item>) ois.readObject();
            ois.close();
            fis.close();
            adapter.notifyDataSetChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        writeList(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        readList(getContext());
        adapter = null;
        adapter = new ItemAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}