package com.example.aswanabidin.traveker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.traveker.CardHome.HalamanItenerary;
import com.example.aswanabidin.traveker.Model.IteneraryModel;
import com.example.aswanabidin.traveker.Model.ListHotelsModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class HalamanDetailHotel extends AppCompatActivity {

    private TextView nama, location, description;
    String snama, slocation, sdescription, surl;
    private ImageView imageView;
    private StorageReference mStorageref;
    Context context;

    private DatabaseReference myRef;
    FirebaseDatabase database;

    public static final String FB_STORAGE_PATH = "hotels/";
    public static final String FB_DATABASE_PATH = "hotels";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_detail_hotel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView judul = (TextView) toolbar.findViewById(R.id.location);
        judul.setText("Bandung, Indonesia");

        TextView date = (TextView) toolbar.findViewById(R.id.date);
        date.setText("Sat, 05 Aug 2017, 1 nights(s)");

        nama = (TextView) findViewById(R.id.tvhotels);
        location = (TextView) findViewById(R.id.tvlocation);
        description = (TextView) findViewById(R.id.tvdescription);
        imageView = (ImageView) findViewById(R.id.imghotel);

        //untuk mengambil data dari holder hoteladapter dan menampilkan di textview
        Bundle bundle;
        bundle = getIntent().getExtras();
        if (bundle != null) {
            ListHotelsModel listHotelsModel = bundle.getParcelable("hotels");
            snama = listHotelsModel.getNama();
            slocation = listHotelsModel.getLocation();
            sdescription = listHotelsModel.getDescription();
            surl = listHotelsModel.getUrl();
            nama.setText(listHotelsModel.getNama());
            location.setText(listHotelsModel.getLocation());
            description.setText(listHotelsModel.getDescription());
            Picasso.with(getApplication()).load(surl).fit().centerCrop().into(imageView);
        }
    }

    // kodingan tombol back
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanListHotels.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
