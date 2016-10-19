package com.example.george.memento.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.george.memento.EditItemActivity;
import com.example.george.memento.Item;
import com.example.george.memento.R;

import java.util.ArrayList;

public class ItemAdapterOld extends ArrayAdapter<Item> {

    private Context context;

    private ArrayList<Item> items;

    public ItemAdapterOld(Context context, ArrayList<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int pos) {
        return items.get(pos);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        final Item currentItem = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        titleTextView.setText(currentItem.getTitle());

        TextView yearTextView = (TextView) listItemView.findViewById(R.id.year);
        yearTextView.setText(currentItem.getYear());

        TextView categoryTextView = (TextView) listItemView.findViewById(R.id.category);
        categoryTextView.setText(currentItem.getCategory());

        RatingBar ratingBar = (RatingBar) listItemView.findViewById(R.id.rating_bar);
        ratingBar.setRating(currentItem.getRating());

        TextView descriptionTextView = (TextView) listItemView.findViewById(R.id.description);
        descriptionTextView.setText(currentItem.getDescription());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(currentItem.getDate());

        ImageView deleteImageView = (ImageView) listItemView.findViewById(R.id.btn_delete);
        deleteImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to delete «" + items.get(position).getTitle() + "»?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                items.remove(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        ImageView editImageView = (ImageView) listItemView.findViewById(R.id.btn_edit);
        final Intent intent = new Intent(context, EditItemActivity.class);
        editImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String category = items.get(position).getCategory();
                intent.putExtra("category", category);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });

        return listItemView;
    }

}
