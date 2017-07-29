package com.example.aswanabidin.traveker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aswanabidin.traveker.Adapter.FlightAdapter;
import com.example.aswanabidin.traveker.CardHome.HalamanFlights;
import com.example.aswanabidin.traveker.CardHome.HalamanItenerary;
import com.example.aswanabidin.traveker.Model.ListFlightsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by aswanabidin on 7/29/17.
 */

public class HalamanListFlights extends AppCompatActivity {

    private ListFlightsModel listFlightsModel;
    private String TAG = "HalamanListFlights";

    private ArrayList<ListFlightsModel> listFlightsModels = new ArrayList<>();
    private Context context;
    private FlightAdapter mAdapter;
    private RecyclerView recyclerView;

    private DatabaseReference myRef;
    FirebaseDatabase database;

    public static final String FB_STORAGE_PATH = "flights/";
    public static final String FB_DATABASE_PATH = "flights";

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_list_flights);

        // custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView origin = (TextView) toolbar.findViewById(R.id.originflights);
        origin.setText("Makassar - Bandung");

        TextView date = (TextView) toolbar.findViewById(R.id.dateflights);
        date.setText("Sat, 05 Aug");

        //progressbar
        progressBar = (ProgressBar) findViewById(R.id.progress_circle);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#429F46"), PorterDuff.Mode.SRC_ATOP);

        mAdapter = new FlightAdapter(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.listFlights);
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

                listFlightsModels = new ArrayList<ListFlightsModel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ListFlightsModel value = dataSnapshot1.getValue(ListFlightsModel.class);
                    ListFlightsModel flights = new ListFlightsModel();
                    String maskapai = value.getMaskapai();
                    String time = value.getTime();
                    String harga = value.getHarga();
                    String url = value.getUrl();
                    flights.setMaskapai(maskapai);
                    flights.setTime(time);
                    flights.setHarga(harga);
                    flights.setUrl(url);
                    mAdapter.addData(flights);
                }
                progressBar.setVisibility(View.GONE); //progress bar berhenti ketika cardview muncul

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //failed to read value
                Log.w("Hello", "Failed to read value.", databaseError.toException());
            }
        });
    }

    // kodingan tombol back
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanFlights.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
