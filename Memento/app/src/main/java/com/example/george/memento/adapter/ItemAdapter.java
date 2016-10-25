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
import com.example.george.memento.MainActivity;
import com.example.george.memento.R;
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
        holder.titleTextView.setText(items.get(position).getTitle());
        holder.yearTextView.setText(items.get(position).getYear());
        holder.categoryTextView.setText(items.get(position).getCategory());
        holder.ratingBar.setRating(items.get(position).getRating());
        holder.descriptionTextView.setText(items.get(position).getDescription());
        holder.date.setText(items.get(position).getDate());

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to delete «" + items.get(holder.getAdapterPosition()).getTitle() + "»?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                items.remove(holder.getAdapterPosition());
                                int tabPosition = MainActivity.tabLayout.getSelectedTabPosition();
                                switch (tabPosition) {
                                    case 0:
                                        BooksFragment.removeItem(holder.getAdapterPosition());
                                        BooksFragment.writeList(context);
                                        break;
                                    case 1:
                                        BooksEduFragment.removeItem(holder.getAdapterPosition());
                                        BooksEduFragment.writeList(context);
                                        break;
                                    case 2:
                                        MoviesFragment.removeItem(holder.getAdapterPosition());
                                        MoviesFragment.writeList(context);
                                        break;
                                    case 3:
                                        ShowsFragment.removeItem(holder.getAdapterPosition());
                                        ShowsFragment.writeList(context);
                                        break;
                                    case 4:
                                        GamesFragment.removeItem(holder.getAdapterPosition());
                                        GamesFragment.writeList(context);
                                        break;
                                }
                                notifyDataSetChanged();
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

        final Intent intent = new Intent(context, EditItemActivity.class);
        holder.editImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String category = items.get(holder.getAdapterPosition()).getCategory();
                intent.putExtra("category", category);
                intent.putExtra("position", holder.getAdapterPosition());
                context.startActivity(intent);
                notifyDataSetChanged();
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView description = (TextView) v.findViewById(R.id.description);
                if (description.getVisibility() == View.GONE) {
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
            categoryTextView = (TextView) itemView.findViewById(R.id.category);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rating_bar);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);
            deleteImageView = (ImageView) itemView.findViewById(R.id.btn_delete);
            editImageView = (ImageView) itemView.findViewById(R.id.btn_edit);
        }
    }

}
