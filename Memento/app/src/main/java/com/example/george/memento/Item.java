package com.example.george.memento;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Item implements Serializable {

    private String title;

    private String year;

    private String category;

    private float rating;

    private String description;

    private String date;

    public Item(String title, String year, String category, float rating, String description) {
        this.title = title;
        this.year = year;
        this.category = category;
        this.rating = rating;
        this.description = description;

        DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        this.date = df.format(Calendar.getInstance().getTime());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
