package com.example.aswanabidin.traveker.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by aswanabidin on 7/22/17.
 */

public class Itenerary implements Parcelable {


    public static final Creator<Itenerary> CREATOR = new Creator<Itenerary>() {
        @Override
        public Itenerary createFromParcel(Parcel in) {
            return new Itenerary(in);
        }

        @Override
        public Itenerary[] newArray(int size) {
            return new Itenerary[size];
        }
    };


    String location;
    String tourplace;
    String date;
    String title;
    String description;
    String imageite;



    public Itenerary(String location, String tourplace, String date, String title, String description, String imageite) {
        this.location = location;
        this.tourplace = tourplace;
        this.date = date;
        this.title = title;
        this.description = description;
        this.imageite = imageite;
    }


    protected Itenerary(Parcel in) {
        location = in.readString();
        tourplace = in.readString();
        title = in.readString();
        description = in.readString();
        imageite = in.readString();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTourplace() {
        return tourplace;
    }

    public void setTourplace(String tourplace) {
        this.tourplace = tourplace;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageite() {
        return imageite;
    }

    public void setImageite(String imageite) {
        this.imageite = imageite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(location);
        parcel.writeString(tourplace);
        parcel.writeString(title);
        parcel.writeString(description);
    }

    public Itenerary(){

    }
}
