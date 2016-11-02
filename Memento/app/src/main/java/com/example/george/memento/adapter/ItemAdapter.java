package com.example.george.memento.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.george.memento.EditItemActivity;
import com.example.george.memento.Item;
import com.example.george.memento.R;
import com.example.george.memento.SearchableActivity;
import com.example.george.memento.fragment.BooksEduFragment;
import com.example.george.memento.fragment.BooksFragment;
import com.example.george.memento.fragment.GamesFragment;
import com.example.george.memento.fragment.MoviesFragment;
import com.example.george.memento.fragment.ShowsFragment;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private Context context;

    private ArrayList<Item> items;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemAdapter.ViewHolder holder, int position) {
//        int colorResource = items.get(position).getColorResource();
//        int color = ContextCompat.getColor(context, colorResource);
//        holder.cardView.setCardBackgroundColor(color);

        holder.titleTextView.setText(items.get(position).getTitle());

        if ("".equals(items.get(position).getYear())) {
            holder.yearTextView.setVisibility(View.GONE);
        } else {
            holder.yearTextView.setText(items.get(position).getYear());
        }

        if ("".equals(items.get(position).getGenre())) {
            holder.genreTextView.setVisibility(View.GONE);
        } else {
            holder.genreTextView.setText(items.get(position).getGenre());
        }

        holder.categoryTextView.setText(items.get(position).getCategory());
        holder.ratingBar.setRating(items.get(position).getRating());
        holder.descriptionTextView.setText(items.get(position).getDescription());
        holder.date.setText(items.get(position).getDate());

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete «" + items.get(holder.getAdapterPosition()).getTitle() + "»?")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String category = items.get(holder.getAdapterPosition()).getCategory();
                                switch (category) {
                                    case "Books":
                                        BooksFragment.removeItem(items.get(holder.getAdapterPosition()));
                                        BooksFragment.writeList(context);
                                        break;
                                    case "Books(edu)":
                                        BooksEduFragment.removeItem(items.get(holder.getAdapterPosition()));
                                        BooksEduFragment.writeList(context);
                                        break;
                                    case "Movies":
                                        MoviesFragment.removeItem(items.get(holder.getAdapterPosition()));
                                        MoviesFragment.writeList(context);
                                        break;
                                    case "Shows":
                                        ShowsFragment.removeItem(items.get(holder.getAdapterPosition()));
                                        ShowsFragment.writeList(context);
                                        break;
                                    case "Games":
                                        GamesFragment.removeItem(items.get(holder.getAdapterPosition()));
                                        GamesFragment.writeList(context);
                                        break;
                                }
                                try {
                                    SearchableActivity.doSearch(context);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        final Intent intent = new Intent(context, EditItemActivity.class);
        holder.editImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent.putExtra("item", items.get(holder.getAdapterPosition()));
                context.startActivity(intent);
                notifyDataSetChanged();
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView description = (TextView) v.findViewById(R.id.description);
                if (description.getVisibility() == View.GONE && !("".equals(description.getText()))) {
                    description.setVisibility(View.VISIBLE);
                } else {
                    description.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView titleTextView;
        TextView yearTextView;
        TextView genreTextView;
        TextView categoryTextView;
        RatingBar ratingBar;
        TextView descriptionTextView;
        TextView date;
        ImageView deleteImageView;
        ImageView editImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            yearTextView = (TextView) itemView.findViewById(R.id.year);
            genreTextView = (TextView) itemView.findViewById(R.id.genre);
            categoryTextView = (TextView) itemView.findViewById(R.id.category);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rating_bar);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);
            deleteImageView = (ImageView) itemView.findViewById(R.id.btn_delete);
            editImageView = (ImageView) itemView.findViewById(R.id.btn_edit);
        }
    }

}
