package com.example.aswanabidin.traveker.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aswanabidin.traveker.Adapter.SpinnerAdapter;
import com.example.aswanabidin.traveker.Model.ItemAirportModel;
import com.example.aswanabidin.traveker.Model.ListFlightsModel;
import com.example.aswanabidin.traveker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class FlightBookingFragments extends Fragment {

    private TextView namamaskapai, tujuan, time, date, harga;
    String snamamaskapai, stujuan, stime, sdate, sharga, surl;
    private ImageView imageView;
    private EditText nameonidcard;
    private StorageReference mStorageRef;
    Context context;
    private Button btncontinuebook;

    private DatabaseReference myRef;
    FirebaseDatabase database;

    public static final String FB_STORAGE_PATH = "flights/";
    public static final String FB_DATABASE_PATH = "flights";

    private Spinner sp;

    public FlightBookingFragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_booking, container, false);
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);

//        String strtext = getArguments().getString("flights");

        namamaskapai = (TextView) view.findViewById(R.id.tvbooknama);
        tujuan = (TextView) view.findViewById(R.id.tvbooktujuan);
        time = (TextView) view.findViewById(R.id.tvbooktime);
        date = (TextView) view.findViewById(R.id.tvbookdate);
        harga = (TextView) view.findViewById(R.id.tvbookharga);
        imageView = (ImageView) view.findViewById(R.id.imgbookmaskapai);
        nameonidcard = (EditText) view.findViewById(R.id.etnameonidcard);
        btncontinuebook = (Button) view.findViewById(R.id.btncontinuebook);
        btncontinuebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), FlightReviewFragments.class);
//                startActivity(in);
                Toast.makeText(view.getContext().getApplicationContext(),"Clicked",5000).show();
            }
        });


        //untuk mengambil data dari holder flightsadapter dan menampilkan di textview
        Bundle bundle = new Bundle();
        bundle = getActivity().getIntent().getExtras();
        if (getArguments() != null){
            String mParam1 = getArguments().getString("flights");
//            snamamaskapai = listFlightsModel.getMaskapai();
//            stime = listFlightsModel.getTime();
//            sharga = listFlightsModel.getHarga();
//            surl = listFlightsModel.getUrl();
//
//            namamaskapai.setText(listFlightsModel.getMaskapai());
//            time.setText(listFlightsModel.getTime());
//            harga.setText(listFlightsModel.getHarga());
//            Picasso.with(getActivity().getApplication()).load(surl).into(imageView);
        }

        //list dialog daftar passenger
        ArrayList<ItemAirportModel> listpass = new ArrayList<>();
        listpass.add(new ItemAirportModel("Mr."));
        listpass.add(new ItemAirportModel("Mrs."));
        listpass.add(new ItemAirportModel("Ms."));
        listpass.add(new ItemAirportModel("Ms."));

        //list spinner dialog passenger
        sp = (Spinner) view.findViewById(R.id.spintitle);
        final SpinnerAdapter adapter = new SpinnerAdapter(getActivity(),R.layout.spinner_layout,R.id.txtspinner,listpass){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View itemView = super.getView(position, convertView, parent);
                if(position == getCount()){
                    ((TextView)itemView.findViewById(R.id.txtspinner)).setText("");
                    ((TextView)itemView.findViewById(R.id.txtspinner)).setHint("Title");
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
        sp.setPrompt("Title");

        return view;

    }



}
