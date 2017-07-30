package com.example.aswanabidin.traveker.CardHome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aswanabidin.traveker.Adapter.SpinnerAdapter;
import com.example.aswanabidin.traveker.HalamanHome;
import com.example.aswanabidin.traveker.HalamanListHotels;
import com.example.aswanabidin.traveker.HalamanListTours;
import com.example.aswanabidin.traveker.Model.ItemAirportModel;
import com.example.aswanabidin.traveker.R;

import java.util.ArrayList;

public class HalamanTours extends AppCompatActivity {

    private Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_tours);

        // inisialisasi variabel

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView judul = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        judul.setText("Search Tours");

        search = (Button) findViewById(R.id.btnsearchtours);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanTours.this, HalamanListTours.class);
                startActivity(intent);
            }
        });

        //list dialog daftar destination tourplace
        ArrayList<ItemAirportModel> listdest = new ArrayList<>();
        listdest.add(new ItemAirportModel("Jakarta, Indonesia (Soekarno Hatta) - CGK"));
        listdest.add(new ItemAirportModel("Yogakarta, Indonesia (Adi Sucipto) - JOG"));
        listdest.add(new ItemAirportModel("Makassar, Indonesia (Sultan Hasanuddin)"));
        listdest.add(new ItemAirportModel("Bandung, Indonesia (Husein Sastranegara) - BDO"));
        listdest.add(new ItemAirportModel("Medan, Indonesia (Kuala Namu) - KNO"));
        listdest.add(new ItemAirportModel("Denpasar, Indonesia (Ngurah Rai) - DPS"));
        listdest.add(new ItemAirportModel("Surabaya, Indonsia (Juanda) - SUB"));
        listdest.add(new ItemAirportModel("Padang, Indonesia (Minangkabau) - PDG"));
        listdest.add(new ItemAirportModel("Batam, Indonesia (Hang Nadim Airpot) - BTH"));
        listdest.add(new ItemAirportModel("Banda Aceh, Indonesia (Sultan Iskandar Muda) - BTJ"));
        listdest.add(new ItemAirportModel("Pekanbaru, Indonesia (Sultan Syarfi Kasim II) - PKU"));
        listdest.add(new ItemAirportModel("Palembang, Indonesia (Sultan Mahmud Badaruddin II) - PLM"));
        listdest.add(new ItemAirportModel("Tanjungpinang, Indonesia (Raja Haji Fisabilillah) - TNJ"));
        listdest.add(new ItemAirportModel("Bengkulu, Indonesia (Fatmawati Soekarno) - BKS"));
        listdest.add(new ItemAirportModel("Bandar Lampung, Indonesia (Radin Inten II) - TKG"));
        listdest.add(new ItemAirportModel("Solo, Indonesia (Adisumarmo) - SOC"));
        listdest.add(new ItemAirportModel("Semarang, Indonesia (Achmad Yani) - SRG"));
        listdest.add(new ItemAirportModel("Masalembo, Indonesia (Valia Rahma) - MSI"));
        listdest.add(new ItemAirportModel("Lombok, Indonesia (Lombok) - LOP"));
        listdest.add(new ItemAirportModel("Tarakan, Indonesia (Juwata) - TRK"));
        listdest.add(new ItemAirportModel("Berau, Indonesia (Kalimarau) - BEJ"));
        listdest.add(new ItemAirportModel("Banjarmasin, Indonesia (Syamsuddin-Noor) - BDJ"));
        listdest.add(new ItemAirportModel("Manado, Indonesia (Sam Ratulangi) - MDC"));
        listdest.add(new ItemAirportModel("Kendari, Indonesia (Haluoleo) - KDI"));
        listdest.add(new ItemAirportModel("Nabire, Indonesia (Yos Sudarso) - NBX"));
        listdest.add(new ItemAirportModel("Oksibil, Indonesia (Iskak) - ORG"));
        listdest.add(new ItemAirportModel("Jayapura, Indonesia (Sentani) - DJJ"));
        listdest.add(new ItemAirportModel("Biak, Indonesia (Frans Kaisiepo) - BIK"));
        listdest.add(new ItemAirportModel("Tembagapura, Indonesia (Mozes Kilangin) - TIM"));
        listdest.add(new ItemAirportModel("Merauke, Indonesia (Mopah) - MKQ"));
        listdest.add(new ItemAirportModel("Tokyo, Jepang (Haneda) - HND"));
        listdest.add(new ItemAirportModel("Hilingdon, London (Heathrow) - LHR"));
        listdest.add(new ItemAirportModel("Roissy-en, Paris (Paris-Charles de Gaulle) - CDG"));
        listdest.add(new ItemAirportModel("Berlin, Jerman (Berlin-Schonefeld) - FBS"));
        listdest.add(new ItemAirportModel("Berlin, Jerman (Berlin-Schonefeld) - FBS"));


        //list spinner dialog destination tourplace
        Spinner sp1 = (Spinner) findViewById(R.id.spindestinationtours);
        final SpinnerAdapter adapter1 = new SpinnerAdapter(this,R.layout.spinner_layout,R.id.txtspinner,listdest){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View itemView = super.getView(position, convertView, parent);
                if(position == getCount()){
                    ((TextView)itemView.findViewById(R.id.txtspinner)).setText("");
                    ((TextView)itemView.findViewById(R.id.txtspinner)).setHint("Destination");
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
        sp1.setPrompt("Destination");
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
