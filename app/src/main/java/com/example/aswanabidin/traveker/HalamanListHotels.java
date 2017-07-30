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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aswanabidin.traveker.Adapter.HotelAdapter;
import com.example.aswanabidin.traveker.CardHome.HalamanHotel;
import com.example.aswanabidin.traveker.Model.ListHotelsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HalamanListHotels extends AppCompatActivity {

    private ListHotelsModel listHotelsModel;
    private String TAG = "HalamanListHotels";

    private ArrayList<ListHotelsModel> listHotelsModels = new ArrayList<>();
    private Context context;
    private HotelAdapter mAdapter;
    private RecyclerView recyclerView;

    private DatabaseReference myRef;
    FirebaseDatabase database;

    public static final String FB_STORAGE_PATH = "hotels/";
    public static final String FB_DATABASE_PATH = "hotels";

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_list_hotels);

        // custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView origin = (TextView) toolbar.findViewById(R.id.locationhotel);
        origin.setText("Bandung, Indonesia");

        TextView date = (TextView) toolbar.findViewById(R.id.datehotel);
        date.setText("Sat, 05 Aug 2017, 1 night(s)");

        //progressbar
        progressBar = (ProgressBar) findViewById(R.id.progress_circle);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#429F46"), PorterDuff.Mode.SRC_ATOP);

        mAdapter = new HotelAdapter(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.listHotels);
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

                listHotelsModels = new ArrayList<ListHotelsModel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ListHotelsModel value = dataSnapshot1.getValue(ListHotelsModel.class);
                    ListHotelsModel hotels = new ListHotelsModel();
                    String nama = value.getNama();
                    String location = value.getLocation();
                    String harga = value.getHarga();
                    String url = value.getUrl();
                    hotels.setNama(nama);
                    hotels.setLocation(location);
                    hotels.setHarga(harga);
                    hotels.setUrl(url);
                    mAdapter.addData(hotels);
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
            Intent intent = new Intent(this, HalamanHotel.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
