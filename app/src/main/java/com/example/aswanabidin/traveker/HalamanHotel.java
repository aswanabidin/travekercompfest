package com.example.aswanabidin.traveker;

import android.app.DatePickerDialog;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class HalamanHotel extends AppCompatActivity {

    private EditText checkIn;
    private EditText checkOut;
    private Spinner spguests,sprooms;

    int day, month, year;
    int dayFinal, monthFinal, yearFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_hotel);

        // inisialisasi variabel

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView judul = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        judul.setText("Search Hotels");

        checkIn = (EditText) findViewById(R.id.etcheckin);
        checkOut = (EditText) findViewById(R.id.etcheckout);
        final java.util.Calendar myCalendar = java.util.Calendar.getInstance();

        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                java.util.Calendar mcurrentDate = java.util.Calendar.getInstance();
                year = mcurrentDate.get(java.util.Calendar.YEAR);
                month = mcurrentDate.get(java.util.Calendar.MONTH);
                day = mcurrentDate.get(java.util.Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(HalamanHotel.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        String tanggal;
                        long tanggalpilih = 0;
                        if (selectedmonth < 10) {
                            tanggal = String.valueOf(selectedday + "/0" + (++selectedmonth) + "/" + year);
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
                            Toast.makeText(HalamanHotel.this, "Deadline Harus Melebihi Tanggal Sekarang", Toast.LENGTH_SHORT).show();
                        } else
                            checkIn.setText(tanggal);
                    }
                },year, month, day);
                mDatePicker.setTitle("Check-in Date");
                mDatePicker.show();  }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                java.util.Calendar mcurrentDate = java.util.Calendar.getInstance();
                year = mcurrentDate.get(java.util.Calendar.YEAR);
                month = mcurrentDate.get(java.util.Calendar.MONTH);
                day = mcurrentDate.get(java.util.Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(HalamanHotel.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        String tanggal;
                        long tanggalpilih = 0;
                        if (selectedmonth < 10) {
                            tanggal = String.valueOf(selectedday + "/0" + (++selectedmonth) + "/" + year);
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
                            Toast.makeText(HalamanHotel.this, "Deadline Harus Melebihi Tanggal Sekarang", Toast.LENGTH_SHORT).show();
                        } else
                            checkOut.setText(tanggal);
                    }
                },year, month, day);
                mDatePicker.setTitle("Check-out Date");
                mDatePicker.show();  }
        });

        spguests = (Spinner) findViewById(R.id.spinguests);
        sprooms = (Spinner) findViewById(R.id.spinrooms);

        ArrayAdapter<CharSequence> guests = ArrayAdapter.createFromResource(this,
                R.array.SpinnerGuests, android.R.layout.simple_spinner_item);
        guests.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spguests.setAdapter(guests);

        ArrayAdapter<CharSequence> rooms = ArrayAdapter.createFromResource(this,
                R.array.SpinnerRooms, android.R.layout.simple_spinner_item);
        rooms.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprooms.setAdapter(rooms);

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
}
