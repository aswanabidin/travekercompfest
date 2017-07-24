package com.example.aswanabidin.traveker.Model;

import android.media.Image;

/**
 * Created by aswanabidin on 7/24/17.
 */

public class IteneraryModel {

    public String location;
    public String tourplace;
    public String date;
    public String title;
    public String description;
    public Image image;

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
