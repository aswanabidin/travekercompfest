package com.example.aswanabidin.traveker;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aswanabidin.traveker.Model.Itenerary;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HalamanAddItenerary extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase mDatabase;
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private Button btnsubmit;
    private EditText departureDate, date, location, tourplace, title, description;
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_add_itenerary);

        // inisialisasi variabel

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView judul = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        judul.setText("Add Story");

        mDatabase = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btnsubmit = (Button) findViewById(R.id.btnSubmitstory);
        location = (EditText) findViewById(R.id.etStorylocation);
        tourplace = (EditText) findViewById(R.id.etStorytour);
        date = (EditText) findViewById(R.id.etStoryDate);
        title = (EditText) findViewById(R.id.etStorytitle);
        description = (EditText) findViewById(R.id.etStorydescription);

        progressDialog = new ProgressDialog(this);
        departureDate = (EditText) findViewById(R.id.etStoryDate);
        final java.util.Calendar myCalendar = java.util.Calendar.getInstance();

        departureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                java.util.Calendar mcurrentDate = java.util.Calendar.getInstance();
                year = mcurrentDate.get(java.util.Calendar.YEAR);
                month = mcurrentDate.get(java.util.Calendar.MONTH);
                day = mcurrentDate.get(java.util.Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(HalamanAddItenerary.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        String tanggal;
                        long tanggalpilih = 0;
                        if (selectedmonth < 10) {
                            tanggal = String.valueOf(selectedday + "/" + (++selectedmonth) + "/" + year);
                        } else {
                            tanggal = String.valueOf(selectedday + "/" + (++selectedmonth) + "/" + year);
                        }
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = sdf.parse(tanggal);
                            tanggalpilih = date.getTime();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (tanggalpilih < System.currentTimeMillis()) {
                            Toast.makeText(HalamanAddItenerary.this, "Deadline Harus Melebihi Tanggal Sekarang", Toast.LENGTH_SHORT).show();
                        } else
                            departureDate.setText(tanggal);
                    }
                },year, month, day);
                mDatePicker.setTitle("Departure Date");
                mDatePicker.show();  }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //menyimpan file string di get untuk dikirim ke database
                String slocation = location.getText().toString().trim();
                String stourplace = tourplace.getText().toString().trim();
                String sdate = date.getText().toString().trim();
                String stitle = title.getText().toString().trim();
                String sdescription = description.getText().toString().trim();

                //membuat objek itenerary
                Itenerary itenerary = new Itenerary();

                //menambahkan values
                itenerary.setLocation(slocation);
                itenerary.setTourplace(stourplace);
                itenerary.setDate(sdate);
                itenerary.setTitle(stitle);
                itenerary.setDescription(sdescription);

                //menyimpan data ke firebase
                progressDialog.setMessage("Plese Wait...");
                progressDialog.show();
                ref.push().setValue(itenerary);
                ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {

                    @Override
                    public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                        for (com.google.firebase.database.DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            //ambil data dari snapshot
                            Itenerary itenerary = postSnapshot.getValue(Itenerary.class);
                        }
                        startActivity(new Intent(getApplicationContext(), HalamanItenerary.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed : " + DatabaseError.UNKNOWN_ERROR);

                    }
                });

            }
        });


    }

    // kodingan tombol back <-
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            android.app.FragmentManager fm = getFragmentManager();
            fm.popBackStack();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {


    }
}
