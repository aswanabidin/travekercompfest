package com.example.aswanabidin.traveker.CardHome;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aswanabidin.traveker.Adapter.RecyclerAdapter;
import com.example.aswanabidin.traveker.HalamanAddItenerary;
import com.example.aswanabidin.traveker.HalamanDetailItenerary;
import com.example.aswanabidin.traveker.HalamanHome;
import com.example.aswanabidin.traveker.Model.IteneraryModel;
import com.example.aswanabidin.traveker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HalamanItenerary extends AppCompatActivity {

    private IteneraryModel iteneraryModel;
    private String TAG = "HalamanItenerary";

    private ArrayList<IteneraryModel> iteneraryModelList = new ArrayList<>();
    private Context context;
    private RecyclerAdapter mAdapter;
    private RecyclerView recyclerView;
    private CardView cardView;

    private DatabaseReference myRef;
    FirebaseDatabase database;

    public static final String FB_STORAGE_PATH = "image/";
    public static final String FB_DATABASE_PATH = "image";


    @BindView(R.id.listItenerary) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_halaman_itenerary);
        ButterKnife.bind(this);

        // customize toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView judul = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        judul.setText("Itenerary");

        /*RecylcerView*/
        //menampilkan recyclerview yang ada pada file layout dengan id recylcerview
        recyclerView = (RecyclerView) findViewById(R.id.listItenerary);

        //membuat adapter baru untuk recylcerview
        mAdapter = new RecyclerAdapter(getApplicationContext());
        //menset nlai dari adapter
        recyclerView.setAdapter(mAdapter);

        //menset ukuran
        recyclerView.setHasFixedSize(true);

        //menset layout manager dan menampilkan daftar/list
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //menampilkan detail itenerary pada cardview
        cardView = (CardView) findViewById(R.id.cardview_itenerary);

        //instansiasi firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(FB_DATABASE_PATH);
        myRef.keepSynced(true);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                iteneraryModelList = new ArrayList<IteneraryModel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    IteneraryModel value = dataSnapshot1.getValue(IteneraryModel.class);
                    IteneraryModel itenerary = new IteneraryModel();
                    String location = value.getLocation();
                    String tourplace = value.getTourplace();
                    String date = value.getDate();
                    String title = value.getTitle();
                    String description = value.getDescription();
                    String url = value.getUrl();
                    itenerary.setLocation(location);
                    itenerary.setTourplace(tourplace);
                    itenerary.setDate(date);
                    itenerary.setTitle(title);
                    itenerary.setDescription(description);
                    itenerary.setUrl(url);
                    Log.i("infodata",url);
                    //iteneraryModelList.add(itenerary);
                    mAdapter.addData(itenerary);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //failed to read value
                Log.w("Hello", "Failed to read value.", databaseError.toException());
            }
        });

        //menampilkan gambar write yang ada pada toolbar itenerary
        ImageView storyimg = (ImageView) findViewById(R.id.imgadd);
        // menset click on listener untuk membuka gambar write tersebut ketika di klik
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

    // kodingan tombol back
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

    //kodingan tombol keluar beserta transitionnya
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getBaseContext(), HalamanHome.class);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        startActivity(intent);
        finish();
    }

}
