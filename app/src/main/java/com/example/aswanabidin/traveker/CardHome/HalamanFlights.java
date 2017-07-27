package com.example.aswanabidin.traveker.CardHome;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.aswanabidin.traveker.HalamanHome;
import com.example.aswanabidin.traveker.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HalamanFlights extends AppCompatActivity{

    private EditText departureDate;
    private Spinner sp;
    int day, month, year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_flights);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView judul = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        judul.setText("Search Flights");

        departureDate = (EditText) findViewById(R.id.etdeparture);
        final java.util.Calendar myCalendar = java.util.Calendar.getInstance();

        departureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                java.util.Calendar mcurrentDate = java.util.Calendar.getInstance();
                year = mcurrentDate.get(java.util.Calendar.YEAR);
                month = mcurrentDate.get(java.util.Calendar.MONTH);
                day = mcurrentDate.get(java.util.Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(HalamanFlights.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
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
                            Toast.makeText(HalamanFlights.this, "Deadline Harus Melebihi Tanggal Sekarang", Toast.LENGTH_SHORT).show();
                        } else
                            departureDate.setText(tanggal);
                    }
                },year, month, day);
                mDatePicker.setTitle("Departure Date");
                mDatePicker.show();  }
        });

        sp = (Spinner) findViewById(R.id.spinpassenger);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Kategori, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

    }

    // kodingan tombol back <-
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanHome.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getBaseContext(), HalamanHome.class);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        startActivity(intent);
        finish();
    }


}