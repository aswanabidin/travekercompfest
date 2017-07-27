package com.example.aswanabidin.traveker.Model;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 7/24/17.
 */

public class IteneraryModel implements Parcelable {

    public String location;
    public String tourplace;
    public String date;
    public String title;
    public String description;
    public String url ;

    public IteneraryModel(String location, String tourplace, String date, String title, String description, String url) {
        this.location = location;
        this.tourplace = tourplace;
        this.date = date;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public IteneraryModel() {
    }

    protected IteneraryModel(Parcel in) {
        location = in.readString();
        tourplace = in.readString();
        date = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
    }

    public static final Creator<IteneraryModel> CREATOR = new Creator<IteneraryModel>() {
        @Override
        public IteneraryModel createFromParcel(Parcel in) {
            return new IteneraryModel(in);
        }

        @Override
        public IteneraryModel[] newArray(int size) {
            return new IteneraryModel[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTourplace() {
        return tourplace;
    }

    public void setTourplace(String tourplace) {
        this.tourplace = tourplace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(location);
        parcel.writeString(tourplace);
        parcel.writeString(date);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(url);
    }
}
