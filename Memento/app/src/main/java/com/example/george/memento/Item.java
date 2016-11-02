package com.example.george.memento;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Item implements Serializable {

    private String title;

    private String year;

    private String genre;

    private String category;

    private float rating;

    private String description;

    private String date;

    private int colorResource;

    public Item(String title, String year, String genre, String category, float rating, String description, int colorResource) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.category = category;
        this.rating = rating;
        this.description = description;
        this.colorResource = colorResource;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (Float.compare(item.getRating(), getRating()) != 0) return false;
        if (getColorResource() != item.getColorResource()) return false;
        if (getTitle() != null ? !getTitle().equals(item.getTitle()) : item.getTitle() != null)
            return false;
        if (getYear() != null ? !getYear().equals(item.getYear()) : item.getYear() != null)
            return false;
        if (getGenre() != null ? !getGenre().equals(item.getGenre()) : item.getGenre() != null)
            return false;
        if (getCategory() != null ? !getCategory().equals(item.getCategory()) : item.getCategory() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(item.getDescription()) : item.getDescription() != null)
            return false;
        return getDate() != null ? getDate().equals(item.getDate()) : item.getDate() == null;

    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getRating() != +0.0f ? Float.floatToIntBits(getRating()) : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + getColorResource();
        return result;
    }
}
