package com.example.aswanabidin.traveker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aswanabidin.traveker.CardHome.HalamanItenerary;
import com.example.aswanabidin.traveker.Fragments.FlightBookingFragments;
import com.example.aswanabidin.traveker.Fragments.FlightEticketFragments;
import com.example.aswanabidin.traveker.Fragments.FlightPaymentFragments;
import com.example.aswanabidin.traveker.Fragments.FlightReviewFragments;

public class HalamanBookingFlights extends AppCompatActivity {

    public static TabLayout tabLayout;
    private Fragment fragment;
    private FragmentManager fm;
    private FragmentTransaction tukar;

    private FlightBookingFragments flightBookingFragments;
    private FlightReviewFragments flightsReviewFragments;
    private FlightPaymentFragments flightPaymentFragments;
    private FlightEticketFragments flightEticketFragments;

    private boolean bookingTrue, reviewTrue, paymentTrue, eticketTrue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_booking_flights);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView judul = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        judul.setText("Booking Details");

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        bindWidgetsWithAnEvent();
        setupTabLayout();
        replaceFragment(flightBookingFragments);
    }

    private void setupTabLayout(){
        flightBookingFragments = new FlightBookingFragments();
        flightsReviewFragments = new FlightReviewFragments();
        flightPaymentFragments = new FlightPaymentFragments();
        flightEticketFragments = new FlightEticketFragments();
        tabLayout.addTab(tabLayout.newTab().setText("1 Booking"), true);
        tabLayout.addTab(tabLayout.newTab().setText("2 Review"));
        tabLayout.addTab(tabLayout.newTab().setText("3 Payment"));
        tabLayout.addTab(tabLayout.newTab().setText("4 E-Ticket"));
    }

    private void bindWidgetsWithAnEvent() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case 0:
                replaceFragment(flightBookingFragments);
                TabLayout.Tab tab = tabLayout.getTabAt(0);
                tab.select();
                break;
            case 1:
                replaceFragment(flightsReviewFragments);
                TabLayout.Tab tab1 = tabLayout.getTabAt(1);
                tab1.select();
                break;
            case 2:
                replaceFragment(flightPaymentFragments);
                TabLayout.Tab tab2 = tabLayout.getTabAt(2);
                tab2.select();
                break;
            case 3:
                replaceFragment(flightEticketFragments);
                TabLayout.Tab tab3 = tabLayout.getTabAt(3);
                tab3.select();
                break;
        }
    }


    public void replaceFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainframe, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    // kodingan tombol back <-
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanListFlights.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Getter Setter
    public boolean isBookingTrue() {
        return bookingTrue;
    }

    public void setBookingTrue(boolean bookingTrue) {
        this.bookingTrue = bookingTrue;
    }

    public boolean isReviewTrue() {
        return reviewTrue;
    }

    public void setReviewTrue(boolean reviewTrue) {
        this.reviewTrue = reviewTrue;
    }

    public boolean isPaymentTrue() {
        return paymentTrue;
    }

    public void setPaymentTrue(boolean paymentTrue) {
        this.paymentTrue = paymentTrue;
    }

    public boolean isEticketTrue() {
        return eticketTrue;
    }

    public void setEticketTrue(boolean eticketTrue) {
        this.eticketTrue = eticketTrue;
    }



}
