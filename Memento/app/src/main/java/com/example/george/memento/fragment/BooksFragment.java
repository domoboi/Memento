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

    private static ArrayList<Item> items;

    private RecyclerView recyclerView;

    private static RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    public BooksFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_list, container, false);
        items = new ArrayList<Item>();
        items.add(new Item("Deadpool", "2016", CATEGORY, 1f, "Great!"));
        items.add(new Item("Deadpool", "2016", CATEGORY, 2f, "Great!"));
        items.add(new Item("Deadpool", "2016", CATEGORY, 3f, "Great!"));
        items.add(new Item("Deadpool", "2016", CATEGORY, 4f, "Great!"));
        items.add(new Item("Deadpool", "2016", CATEGORY, 5f, "Great!"));

        readList(getContext());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.list);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ItemAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    public static void addItem(int position, Item item) {
        items.add(position, item);
        adapter.notifyDataSetChanged();
    }

    public static void removeItem(int position) {
        items.remove(position);
        adapter.notifyDataSetChanged();
    }

    public static Item getItem(int position) {
        return items.get(position);
    }

    public static void writeList(Context c){
        try{
            FileOutputStream fos = c.openFileOutput(CATEGORY + ".dat", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
            fos.close();
        } catch(Exception ex){
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
        } catch(Exception ex){
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
    }
}