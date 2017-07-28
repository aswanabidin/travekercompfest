package com.example.aswanabidin.traveker;

import android.animation.LayoutTransition;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.transition.TransitionManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.aswanabidin.traveker.CardHome.HalamanFlights;
import com.example.aswanabidin.traveker.CardHome.HalamanHotel;
import com.example.aswanabidin.traveker.CardHome.HalamanItenerary;
import com.example.aswanabidin.traveker.CardHome.HalamanTours;
import com.example.aswanabidin.traveker.Sliders.ViewPagerAdapter;
import com.example.aswanabidin.traveker.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HalamanHome extends AppCompatActivity {

    public ImageView logo;
    public static TabLayout tabLayout;

    private ProgressDialog progressDialog;
    private LinearLayout mLinearLayout;
    private static final String TAG = "HalamanHome";
    private Context mContext = HalamanHome.this;
    private static final int ACTIVITY_NUM = 0;

    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;

    ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_home);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);




//        Log.d(TAG, "onCreate: starting.");

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        logo = (ImageView) toolbar.findViewById(R.id.logotravekerwidth);

        setupBottomNavigationView();

//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CardView flights = (CardView) findViewById(R.id.flights);

//        sliderView = (SliderView) findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);
        progressDialog = new ProgressDialog(this);
//        setupSlider();

        flights = (CardView) findViewById(R.id.flights);

        flights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flights(view);
            }
        });

        final CardView hotels = (CardView) findViewById(R.id.hotels);

        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotels(view);
            }
        });

        final CardView itenerary = (CardView) findViewById(R.id.itenerary);

        itenerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itenerary(view);
            }
        });

        final CardView tours = (CardView) findViewById(R.id.tours);

        tours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tours(view);
            }
        });

        LayoutTransition lt = new LayoutTransition();
        lt.enableTransitionType(LayoutTransition.CHANGING);
        lt.disableTransitionType(LayoutTransition.DISAPPEARING);
//        setContentView(lt);

    }

    //timer slider show image
    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            HalamanHome.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }


    /**
     * BottomNavigation setup
     */

    private void setupBottomNavigationView(){
        Log.d(TAG, "setupNottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(HalamanHome.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    public void flights(View view) {
        Intent intent = new Intent(getBaseContext(), HalamanFlights.class);
        startActivity(intent);
        finish();
    }

    public void hotels(View view) {
        Intent intent = new Intent(getBaseContext(), HalamanHotel.class);
        startActivity(intent);
        finish();
    }

    public void itenerary(View view) {
        Intent intent = new Intent(getBaseContext(), HalamanItenerary.class);
        startActivity(intent);
        finish();
    }

    public void tours(View view) {
        Intent intent = new Intent(this, HalamanTours.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


}
