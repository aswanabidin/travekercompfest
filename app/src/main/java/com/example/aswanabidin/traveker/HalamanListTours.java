package com.example.aswanabidin.traveker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aswanabidin.traveker.Adapter.HotelAdapter;
import com.example.aswanabidin.traveker.Adapter.ToursAdapter;
import com.example.aswanabidin.traveker.CardHome.HalamanHotel;
import com.example.aswanabidin.traveker.Model.ListHotelsModel;
import com.example.aswanabidin.traveker.Model.ListToursModel;
import com.example.aswanabidin.traveker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HalamanListTours extends AppCompatActivity {

    private ListToursModel listToursModel;
    private String TAG = "HalamanListTours";

    private ArrayList<ListToursModel> listToursModels = new ArrayList<>();
    private Context context;
    private ToursAdapter mAdapter;
    private RecyclerView recyclerView;

    private DatabaseReference myRef;
    FirebaseDatabase database;

    public static final String FB_STORAGE_PATH = "tours/";
    public static final String FB_DATABASE_PATH = "tours";

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_list_tours);


        // custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView origin = (TextView) toolbar.findViewById(R.id.locationtour);
        origin.setText("Bandung, Indonesia");

        TextView date = (TextView) toolbar.findViewById(R.id.datetour);
        date.setText("Sat, 05 Aug 2017");

        //progressbar
        progressBar = (ProgressBar) findViewById(R.id.progress_circle);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#429F46"), PorterDuff.Mode.SRC_ATOP);

        mAdapter = new ToursAdapter(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.listTours);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        //instansiasi firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(FB_DATABASE_PATH);
        myRef.keepSynced(true);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.VISIBLE); //progress bar mulai

                listToursModels = new ArrayList<ListToursModel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ListToursModel value = dataSnapshot1.getValue(ListToursModel.class);
                    ListToursModel tour = new ListToursModel();
                    String tours = value.getTour();
                    String location = value.getLocation();
                    String harga = value.getHarga();
                    String url = value.getUrl();
                    tour.setTour(tours);
                    tour.setLocation(location);
                    tour.setHarga(harga);
                    tour.setUrl(url);
                    mAdapter.addData(tour);
                }
                progressBar.setVisibility(View.GONE); //progress bar berhenti ketika cardview muncul
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // kodingan tombol back
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanHotel.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
