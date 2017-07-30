package com.example.aswanabidin.traveker.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 7/30/17.
 */

public class ListHotelsModel implements Parcelable {

    private String nama;
    private String location;
    private String harga;
    private String url;

    public ListHotelsModel(String nama, String location, String harga, String url) {
        this.nama = nama;
        this.location = location;
        this.harga = harga;
        this.url = url;
    }

    public ListHotelsModel(){

    }

    protected ListHotelsModel(Parcel in){
        nama = in.readString();
        location = in.readString();
        harga = in.readString();
        url = in.readString();
    }

    public static final Creator<ListHotelsModel> CREATOR = new Creator<ListHotelsModel>() {
        @Override
        public ListHotelsModel createFromParcel(Parcel parcel) {
            return new ListHotelsModel();
        }

        @Override
        public ListHotelsModel[] newArray(int size) {
            return new ListHotelsModel[size];
        }
    };



    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
        parcel.writeString(nama);
        parcel.writeString(location);
        parcel.writeString(harga);
        parcel.writeString(url);
    }
}
