package com.example.aswanabidin.traveker.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 7/29/17.
 */

public class ListFlightsModel implements Parcelable {


    public String maskapai, time, harga, url;

    public ListFlightsModel(String maskapai, String time, String harga, String url) {
        this.maskapai = maskapai;
        this.time = time;
        this.harga = harga;
        this.url = url;
    }

    public ListFlightsModel(){

    }

    protected ListFlightsModel(Parcel in){
        maskapai = in.readString();
        time = in.readString();
        harga = in.readString();
        url = in.readString();
    }

    public static final Creator<ListFlightsModel> CREATOR = new Creator<ListFlightsModel>() {
        @Override
        public ListFlightsModel createFromParcel(Parcel parcel) {
            return new ListFlightsModel();
        }

        @Override
        public ListFlightsModel[] newArray(int size) {
            return new ListFlightsModel[size];
        }
    };

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        parcel.writeString(maskapai);
        parcel.writeString(time);
        parcel.writeString(harga);
        parcel.writeString(url);

    }
}
