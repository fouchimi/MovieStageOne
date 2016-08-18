package com.example.ousmane.movies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ousmane on 8/17/16.
 */
public class Movie implements Parcelable {

    private String title;
    private String image;
    private String synopsis;
    private double rating;
    private String date;
    private String vote;

    public Movie() {

    }

    public Movie(Parcel in){
        this.title = in.readString();
        this.image = in.readString();
        this.synopsis = in.readString();
        this.rating = in.readDouble();
        this.date = in.readString();
        this.vote = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.title);
        dest.writeString(this.image);
        dest.writeString(this.synopsis);
        dest.writeDouble(this.rating);
        dest.writeString(this.date);
        dest.writeString(this.vote);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
