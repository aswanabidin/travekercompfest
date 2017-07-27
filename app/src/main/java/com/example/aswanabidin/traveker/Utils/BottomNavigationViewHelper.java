package com.example.aswanabidin.traveker.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.MenuItem;

import com.example.aswanabidin.traveker.HalamanAccount;
import com.example.aswanabidin.traveker.HalamanBookings;
import com.example.aswanabidin.traveker.HalamanHome;
import com.example.aswanabidin.traveker.HalamanNotifications;
import com.example.aswanabidin.traveker.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import static java.security.AccessController.getContext;

/**
 * Created by aswanabidin on 7/25/17.
 */

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        Intent intent1 = new Intent(context, HalamanHome.class); //ACTIVITY_NUM = 0
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        Bundle bundle1 = ActivityOptionsCompat.makeCustomAnimation(context,
                                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                        context.startActivity(intent1,bundle1);
                        break;

                    case R.id.navigation_booking:
                        Intent intent2 = new Intent(context, HalamanBookings.class); //ACTIVITY_NUM = 1
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        Bundle bundle2 = ActivityOptionsCompat.makeCustomAnimation(context,
                                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                        context.startActivity(intent2,bundle2);
                        break;

                    case R.id.navigation_notification:
                        Intent intent3 = new Intent(context, HalamanNotifications.class); //ACTIVITY_NUM = 2
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        Bundle bundle3 = ActivityOptionsCompat.makeCustomAnimation(context,
                                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                        context.startActivity(intent3,bundle3);
                        break;

                    case R.id.navigation_user:
                        Intent intent4 = new Intent(context, HalamanAccount.class); //ACTIVITY_NUM = 3
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        Bundle bundle4 = ActivityOptionsCompat.makeCustomAnimation(context,
                                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                        context.startActivity(intent4,bundle4);
                        break;
                }
                return false;
            }
        });
    }
}
