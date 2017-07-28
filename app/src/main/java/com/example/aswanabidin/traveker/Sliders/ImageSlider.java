package com.example.aswanabidin.traveker.Sliders;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.aswanabidin.traveker.R;

import static com.example.aswanabidin.traveker.R.id.container;

/**
 * Created by aswanabidin on 7/28/17.
 */

public class ImageSlider extends AppCompatActivity {

    private static final String ARG_PARAM1 = "params";

    private String imageUrls;

    public ImageSlider(){

    }

    public static ImageSlider newInstance(String params) {
        ImageSlider imageSlider = new ImageSlider();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, params);
//        imageSlider.onCreate(params);
        return imageSlider;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);


    }
}
