package com.example.aswanabidin.traveker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.traveker.Adapter.RecyclerAdapter;
import com.example.aswanabidin.traveker.Model.Itenerary;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HalamanItenerary extends AppCompatActivity {

//    @BindView(R.id.listItenerary) RecyclerView recyclerView;

    private RecyclerView recyclerView;
    ArrayList itenerary;
    FirebaseDatabase database;
    List<Itenerary> list;
    Context context;
    DatabaseReference myRef;
    RecyclerView.Adapter adapter;
    Button view;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_itenerary);

        // inisialisasi variabel

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView judul = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        judul.setText("Itenerary");

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        view = (Button) findViewById(R.id.view);

        recyclerView = (RecyclerView) findViewById(R.id.listItenerary);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);

        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("itenerary");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<Itenerary>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Itenerary value = dataSnapshot1.getValue(Itenerary.class);
                    Itenerary fire = new Itenerary();
                    String location = value.getLocation();
                    String tourplace = value.getTourplace();
                    String date = value.getDate();
                    String title = value.getTitle();
                    String description = value.getDescription();
                    fire.setLocation(location);
                    fire.setTourplace(tourplace);
                    fire.setDate(date);
                    fire.setTitle(title);
                    fire.setDescription(description);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Hello", "Failed to read value.", databaseError.toException());

            }
        });

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,HalamanItenerary.this);
//                RecyclerView.LayoutManager recyce = new GridLayoutManager(HalamanItenerary.this,2);
//                recyclerView.setLayoutManager(recyce);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                recyclerView.setAdapter(recyclerAdapter);
//            }
//        });

        // get the button view
        ImageView storyimg = (ImageView) findViewById(R.id.imgadd);
        // set a onclick listener for when the button gets clicked
        storyimg.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(HalamanItenerary.this, HalamanAddItenerary.class);
                startActivity(mainIntent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_halaman_itenerary, menu);
        return true;
    }

    // kodingan tombol back <-
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanHome.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getBaseContext(), HalamanHome.class);
        startActivity(intent);
        finish();
    }
}
