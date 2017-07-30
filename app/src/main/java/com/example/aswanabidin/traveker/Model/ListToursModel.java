package com.example.aswanabidin.traveker.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 7/30/17.
 */

public class ListToursModel implements Parcelable{

    public String tour, location, harga, url;

    public ListToursModel(String tour, String location, String harga, String url) {
        this.tour = tour;
        this.location = location;
        this.harga = harga;
        this.url = url;
    }

    public ListToursModel(){

    }

    protected ListToursModel(Parcel in) {
        tour = in.readString();
        location = in.readString();
        harga = in.readString();
        url = in.readString();
    }

    public static final Creator<ListToursModel> CREATOR = new Creator<ListToursModel>() {
        @Override
        public ListToursModel createFromParcel(Parcel parcel) {
            return new ListToursModel();
        }

        @Override
        public ListToursModel[] newArray(int size) {
            return new ListToursModel[size];
        }
    };


    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
