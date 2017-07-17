package com.example.aswanabidin.traveker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.aswanabidin.traveker.HomeFragment;
import com.example.aswanabidin.traveker.BookingFragment;
import com.example.aswanabidin.traveker.NotificationFragment;
import com.example.aswanabidin.traveker.AccountFragment;


public class HalamanHome extends AppCompatActivity {

//    private TextView mTextMessage;
    public ImageView logo;
    public static TabLayout tabLayout;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    FragmentTransaction fragmentHomeTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentHomeTransaction.replace(R.id.content, homeFragment);
                    fragmentHomeTransaction.commit();
                    return true;

                case R.id.navigation_booking:
                    BookingFragment bookingFragment = new BookingFragment();
                    FragmentTransaction fragmentBookingTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentBookingTransaction.replace(R.id.content, bookingFragment);
                    fragmentBookingTransaction.commit();
                    return true;

                case R.id.navigation_notification:
                    NotificationFragment notificationFragment = new NotificationFragment();
                    FragmentTransaction fragmentNotificationTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentNotificationTransaction.replace(R.id.content, notificationFragment);
                    fragmentNotificationTransaction.commit();
                    return true;

                case R.id.navigation_user:
                    AccountFragment accountFragment = new AccountFragment();
                    FragmentTransaction fragmentAccountTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentAccountTransaction.replace(R.id.content, accountFragment);
                    fragmentAccountTransaction.commit();
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        logo = (ImageView) toolbar.findViewById(R.id.logotravekerwidth);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
