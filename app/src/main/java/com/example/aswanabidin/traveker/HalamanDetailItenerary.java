package com.example.aswanabidin.traveker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.traveker.Adapter.RecyclerAdapter;
import com.example.aswanabidin.traveker.CardHome.HalamanItenerary;
import com.example.aswanabidin.traveker.Model.IteneraryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by aswanabidin on 7/27/17.
 */

public class HalamanDetailItenerary extends AppCompatActivity {

    private TextView title, tourplace, date, location, description;
    String stitle, stourplace, sdate, slocation, sdescription, surl;
    private ImageView sivitenerary, imageView;
    private StorageReference mStorageref;
    Context context;

    private DatabaseReference myRef;
    FirebaseDatabase database;

    public static final String FB_STORAGE_PATH = "image/";
    public static final String FB_DATABASE_PATH = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_detail_itenerary);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView judul = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        judul.setText("Detail Itenerary");

        location = (TextView) findViewById(R.id.locationitenerary);
        tourplace = (TextView) findViewById(R.id.tourplaceitenerary);
        date = (TextView) findViewById(R.id.dateitenerary);
        title = (TextView) findViewById(R.id.titleitenerary);
        description = (TextView) findViewById(R.id.descriptionitenerary);
        imageView = (ImageView) findViewById(R.id.imgitenerary);

        //untuk mengambil data dari holder recycleradapter dan menampilkan di textview
        Bundle bundle;
        bundle = getIntent().getExtras();
        if (bundle != null) {
            IteneraryModel iteneraryModel = bundle.getParcelable("image");
            stitle = iteneraryModel.getTitle();
            slocation = iteneraryModel.getLocation();
            stourplace = iteneraryModel.getTourplace();
            sdate = iteneraryModel.getDate();
            sdescription = iteneraryModel.getDescription();
            surl = iteneraryModel.getUrl();

            location.setText(iteneraryModel.getLocation());
            tourplace.setText(iteneraryModel.getTourplace());
            date.setText(iteneraryModel.getDate());
            title.setText(iteneraryModel.getTitle());
            description.setText(iteneraryModel.getDescription());
            //nge get gambarnya
            Picasso.with(getApplication()).load(surl).fit().centerCrop().into(imageView);
        }
    }

    // kodingan tombol back
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanItenerary.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
