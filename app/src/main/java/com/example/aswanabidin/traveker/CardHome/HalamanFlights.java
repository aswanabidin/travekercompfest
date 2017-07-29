package com.example.aswanabidin.traveker.CardHome;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.aswanabidin.traveker.Adapter.SpinnerAdapter;
import com.example.aswanabidin.traveker.HalamanHome;
import com.example.aswanabidin.traveker.HalamanImportSchedule;
import com.example.aswanabidin.traveker.HalamanListFlights;
import com.example.aswanabidin.traveker.Model.ItemAirportModel;
import com.example.aswanabidin.traveker.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.support.constraint.R.id.parent;

public class HalamanFlights extends AppCompatActivity{

    private EditText departureDate;
    private Spinner sp,sp1;
    int day, month, year;

    Button search;


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

        search = (Button) findViewById(R.id.btnsearchflights);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanFlights.this, HalamanListFlights.class);
                startActivity(intent);
            }
        });

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
                            Toast.makeText(HalamanFlights.this, "No Flights", Toast.LENGTH_SHORT).show();
                        } else
                            departureDate.setText(tanggal);
                    }
                },year, month, day);
                mDatePicker.setTitle("Departure Date");
                mDatePicker.show();  }
        });

        //list dialog daftar passenger
        ArrayList<ItemAirportModel> listpass = new ArrayList<>();
        listpass.add(new ItemAirportModel("1 Passenger(s)"));
        listpass.add(new ItemAirportModel("2 Passenger(s)"));
        listpass.add(new ItemAirportModel("3 Passenger(s)"));
        listpass.add(new ItemAirportModel("4 Passenger(s)"));
        listpass.add(new ItemAirportModel("5 Passenger(s)"));
        listpass.add(new ItemAirportModel("6 Passenger(s)"));
        listpass.add(new ItemAirportModel("7 Passenger(s)"));
        listpass.add(new ItemAirportModel("8 Passenger(s)"));
        listpass.add(new ItemAirportModel("9 Passenger(s)"));
        listpass.add(new ItemAirportModel("10 Passenger(s)"));
        listpass.add(new ItemAirportModel("10 Passenger(s)"));

        //list spinner dialog passenger
        sp = (Spinner) findViewById(R.id.spinpassenger);
        final SpinnerAdapter adapter = new SpinnerAdapter(this,R.layout.spinner_layout,R.id.txtspinner,listpass){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View itemView = super.getView(position, convertView, parent);
                if(position == getCount()){
                    ((TextView)itemView.findViewById(R.id.txtspinner)).setText("");
                    ((TextView)itemView.findViewById(R.id.txtspinner)).setHint("Passenger(s)");
                }
                return itemView;
            }
            @Override
            public int getCount(){
                return super.getCount()-1;
            }
        };
        sp.setAdapter(adapter);
        sp.setSelection(adapter.getCount());
        sp.setPrompt("Passenger(s)");


        // list dialog daftar airport
        ArrayList<ItemAirportModel> list = new ArrayList<>();
        list.add(new ItemAirportModel("Jakarta, Indonesia (Soekarno Hatta) - CGK"));
        list.add(new ItemAirportModel("Yogakarta, Indonesia (Adi Sucipto) - JOG"));
        list.add(new ItemAirportModel("Makassar, Indonesia (Sultan Hasanuddin)"));
        list.add(new ItemAirportModel("Bandung, Indonesia (Husein Sastranegara) - BDO"));
        list.add(new ItemAirportModel("Medan, Indonesia (Kuala Namu) - KNO"));
        list.add(new ItemAirportModel("Denpasar, Indonesia (Ngurah Rai) - DPS"));
        list.add(new ItemAirportModel("Surabaya, Indonsia (Juanda) - SUB"));
        list.add(new ItemAirportModel("Padang, Indonesia (Minangkabau) - PDG"));
        list.add(new ItemAirportModel("Batam, Indonesia (Hang Nadim Airpot) - BTH"));
        list.add(new ItemAirportModel("Banda Aceh, Indonesia (Sultan Iskandar Muda) - BTJ"));
        list.add(new ItemAirportModel("Pekanbaru, Indonesia (Sultan Syarfi Kasim II) - PKU"));
        list.add(new ItemAirportModel("Palembang, Indonesia (Sultan Mahmud Badaruddin II) - PLM"));
        list.add(new ItemAirportModel("Tanjungpinang, Indonesia (Raja Haji Fisabilillah) - TNJ"));
        list.add(new ItemAirportModel("Bengkulu, Indonesia (Fatmawati Soekarno) - BKS"));
        list.add(new ItemAirportModel("Bandar Lampung, Indonesia (Radin Inten II) - TKG"));
        list.add(new ItemAirportModel("Solo, Indonesia (Adisumarmo) - SOC"));
        list.add(new ItemAirportModel("Semarang, Indonesia (Achmad Yani) - SRG"));
        list.add(new ItemAirportModel("Masalembo, Indonesia (Valia Rahma) - MSI"));
        list.add(new ItemAirportModel("Lombok, Indonesia (Lombok) - LOP"));
        list.add(new ItemAirportModel("Tarakan, Indonesia (Juwata) - TRK"));
        list.add(new ItemAirportModel("Berau, Indonesia (Kalimarau) - BEJ"));
        list.add(new ItemAirportModel("Banjarmasin, Indonesia (Syamsuddin-Noor) - BDJ"));
        list.add(new ItemAirportModel("Manado, Indonesia (Sam Ratulangi) - MDC"));
        list.add(new ItemAirportModel("Kendari, Indonesia (Haluoleo) - KDI"));
        list.add(new ItemAirportModel("Nabire, Indonesia (Yos Sudarso) - NBX"));
        list.add(new ItemAirportModel("Oksibil, Indonesia (Iskak) - ORG"));
        list.add(new ItemAirportModel("Jayapura, Indonesia (Sentani) - DJJ"));
        list.add(new ItemAirportModel("Biak, Indonesia (Frans Kaisiepo) - BIK"));
        list.add(new ItemAirportModel("Tembagapura, Indonesia (Mozes Kilangin) - TIM"));
        list.add(new ItemAirportModel("Merauke, Indonesia (Mopah) - MKQ"));
        list.add(new ItemAirportModel("Tokyo, Jepang (Haneda) - HND"));
        list.add(new ItemAirportModel("Hilingdon, London (Heathrow) - LHR"));
        list.add(new ItemAirportModel("Roissy-en, Paris (Paris-Charles de Gaulle) - CDG"));
        list.add(new ItemAirportModel("Berlin, Jerman (Berlin-Schonefeld) - FBS"));
        list.add(new ItemAirportModel("Berlin, Jerman (Berlin-Schonefeld) - FBS"));



        //list spinner dialog asal
        Spinner sp1 = (Spinner) findViewById(R.id.spinnerorigin);
        final SpinnerAdapter adapter1 = new SpinnerAdapter(this,R.layout.spinner_layout,R.id.txtspinner,list){
            //ngeset text hintnya
           @Override
           public View getView(int position, View convertView, ViewGroup parent){
               View itemView = super.getView(position, convertView, parent);
                   if(position == getCount()){
                       ((TextView)itemView.findViewById(R.id.txtspinner)).setText("");
                       ((TextView)itemView.findViewById(R.id.txtspinner)).setHint("Origin"); //hint display
               }
               return itemView;
           }
           @Override
            public int getCount(){
               return super.getCount()-1;
           }
        };
        sp1.setAdapter(adapter1);
        sp1.setSelection(adapter1.getCount());
        sp1.setPrompt("Origin"); //ngeset judul yg ada di dialognya


        //list spinner dialog tujuan
        Spinner sp2 = (Spinner) findViewById(R.id.spinnerdestination);
        final SpinnerAdapter adapter2 = new SpinnerAdapter(this,R.layout.spinner_layout,R.id.txtspinner,list){
            //ngeset text hintnya
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View itemView = super.getView(position, convertView, parent);
                if(position == getCount()){
                    ((TextView)itemView.findViewById(R.id.txtspinner)).setText("");
                    ((TextView)itemView.findViewById(R.id.txtspinner)).setHint("Departure"); //hint display
                }
                return itemView;
            }
            @Override
            public int getCount(){
                return super.getCount()-1;
            }
        };
        sp2.setAdapter(adapter2);
        sp2.setSelection(adapter2.getCount()); //ngeset urutan keberapa hintnya
        sp2.setPrompt("Departure"); //ngeset judul yg ada di dialognya

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
