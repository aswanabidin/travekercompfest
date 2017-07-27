package com.example.aswanabidin.traveker;

import android.animation.LayoutTransition;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.aswanabidin.traveker.CardHome.HalamanFlights;
import com.example.aswanabidin.traveker.CardHome.HalamanHotel;
import com.example.aswanabidin.traveker.CardHome.HalamanItenerary;
import com.example.aswanabidin.traveker.CardHome.HalamanTours;
import com.example.aswanabidin.traveker.Sliders.SliderIndicator;
import com.example.aswanabidin.traveker.Sliders.SliderPagerAdapter;
import com.example.aswanabidin.traveker.Sliders.SliderView;
import com.example.aswanabidin.traveker.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


public class HalamanHome extends AppCompatActivity {

    public ImageView logo;
    public static TabLayout tabLayout;

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    private ProgressDialog progressDialog;
    private SliderView sliderView;
    private LinearLayout mLinearLayout;
    private static final String TAG = "HalamanHome";
    private Context mContext = HalamanHome.this;
    private static final int ACTIVITY_NUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_home);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


//        Log.d(TAG, "onCreate: starting.");

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        logo = (ImageView) toolbar.findViewById(R.id.logotravekerwidth);

        setupBottomNavigationView();

//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CardView flights = (CardView) findViewById(R.id.flights) ;

        sliderView = (SliderView) findViewById(R.id.sliderView);
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

        final CardView hotels = (CardView) findViewById(R.id.hotels) ;

        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotels(view);
            }
        });

        final CardView itenerary = (CardView) findViewById(R.id.itenerary) ;

        itenerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itenerary(view);
            }
        });

        final CardView tours = (CardView) findViewById(R.id.tours) ;

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

//    private void setupSlider() {
//        sliderView.setDurationScroll(800);
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(FragmentSlider.newInstance("https://aswanabidin.files.wordpress.com/2017/07/ic_london.jpg"));
//        fragments.add(FragmentSlider.newInstance("https://aswanabidin.files.wordpress.com/2017/07/ic_sydney.jpg"));
//        fragments.add(FragmentSlider.newInstance("https://aswanabidin.files.wordpress.com/2017/07/ic_berlin.jpg"));
//        fragments.add(FragmentSlider.newInstance("https://aswanabidin.files.wordpress.com/2017/07/ic_paris1.jpg"));
//
//        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
//        sliderView.setAdapter(mAdapter);
//        mIndicator = new SliderIndicator(this, mLinearLayout, sliderView, R.drawable.indicator_circle);
//        mIndicator.setPageCount(fragments.size());
//        mIndicator.show();
//    }

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    HomeFragment homeFragment = new HomeFragment();
//                    FragmentTransaction fragmentHomeTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentHomeTransaction.replace(R.id.content, homeFragment);
//                    fragmentHomeTransaction.commit();
//                    return true;
//
//                case R.id.navigation_booking:
//                    BookingFragment bookingFragment = new BookingFragment();
//                    FragmentTransaction fragmentBookingTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentBookingTransaction.replace(R.id.content, bookingFragment);
//                    fragmentBookingTransaction.commit();
//                    return true;
//
//                case R.id.navigation_notification:
//                    NotificationFragment notificationFragment = new NotificationFragment();
//                    FragmentTransaction fragmentNotificationTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentNotificationTransaction.replace(R.id.content, notificationFragment);
//                    fragmentNotificationTransaction.commit();
//                    return true;
//
//                case R.id.navigation_user:
//                    AccountFragment accountFragment = new AccountFragment();
//                    AfterLoginFragment afterLoginFragment = new AfterLoginFragment();
//                    FragmentTransaction fragmentAccountTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentAccountTransaction.replace(R.id.content, accountFragment);
//                    fragmentAccountTransaction.commit();
//                    return true;
//
//            }
//            return false;
//        }
//
//    };




}
