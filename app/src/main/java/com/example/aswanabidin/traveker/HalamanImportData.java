package com.example.aswanabidin.traveker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HalamanImportData extends AppCompatActivity {

    private Button hotel, tours, flight, detailhotel, detailtours, selectroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_import_data);

        flight = (Button) findViewById(R.id.btnimport);
        hotel = (Button) findViewById(R.id.btnimporthotel);
        tours = (Button) findViewById(R.id.btnimporttours);
        detailhotel = (Button) findViewById(R.id.btnimdetailhotel);
        detailtours = (Button) findViewById(R.id.btnimdetailtours);

        flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanImportData.this, HalamanImportSchedule.class);
                startActivity(intent);
            }
        });

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanImportData.this, HalamanImportHotel.class);
                startActivity(intent);
            }
        });

        tours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanImportData.this, HalamanImportTours.class);
                startActivity(intent);
            }
        });

        detailhotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanImportData.this, HalamanDetailHotel.class);
                startActivity(intent);
            }
        });

        detailtours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanImportData.this, HalamanDetailTours.class);
                startActivity(intent);
            }
        });




    }
}
