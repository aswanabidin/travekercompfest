package com.example.aswanabidin.traveker.Adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.traveker.Fragments.FlightBookingFragments;
import com.example.aswanabidin.traveker.HalamanBookingFlights;
import com.example.aswanabidin.traveker.HalamanDetailItenerary;
import com.example.aswanabidin.traveker.Model.IteneraryModel;
import com.example.aswanabidin.traveker.Model.ListFlightsModel;
import com.example.aswanabidin.traveker.R;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by aswanabidin on 7/29/17.
 */

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.MyViewHolder> {

    StorageReference mStorageRef;
    OnItemClickListener mItemClickListener;
    private ArrayList<ListFlightsModel> listFlightsModels = new ArrayList<>();
    private Activity activity;
    private Context context;

    public FlightAdapter(ArrayList<ListFlightsModel> listFlightsModels){
        this.listFlightsModels = listFlightsModels;
    }

    public FlightAdapter(Context context){
        this.context = context;
    }

    public FlightAdapter(final Fragment currentfragment){

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylcerview_list_flights, parent, false); //layout recyclerview punya
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final ListFlightsModel listFlightsModel = listFlightsModels.get(position);
        holder.maskapai.setText(listFlightsModel.getMaskapai());
        holder.time.setText(listFlightsModel.getTime());
        holder.harga.setText(listFlightsModel.getHarga());
        Picasso.with(context).load(listFlightsModel.getUrl()).into(holder.logomaskapai);

        Bundle bundle = new Bundle();
        bundle.putString("flights","FlightAdapter");
//        bundle.putString("time",listFlightsModel.getTime());
//        bundle.putString("harga",listFlightsModel.getTime());
        FlightBookingFragments flightBookingFragments = new FlightBookingFragments();
        flightBookingFragments.setArguments(bundle);

        holder.itemFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HalamanBookingFlights.class);
//                intent.putExtra("flights",listFlightsModel);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //tadi error nihh
                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return listFlightsModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //viewholder akan mendeskripsikan item view yang ditempatkan di dalam recyclerview

        private TextView maskapai, time, harga;
        private ImageView logomaskapai;
        private View itemFlights;
        ArrayList<ListFlightsModel> listFlightsModels =  new ArrayList<ListFlightsModel>();
        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            maskapai = (TextView) itemView.findViewById(R.id.tvmaskapai);
            time = (TextView) itemView.findViewById(R.id.tvtime);
            harga = (TextView) itemView.findViewById(R.id.tvharga);
            logomaskapai = (ImageView) itemView.findViewById(R.id.imgmaskapai);
            itemFlights = (View) itemView.findViewById(R.id.viewFlights);
        }
    }

    public void  addData(ListFlightsModel im){
        listFlightsModels.add(im);
        notifyDataSetChanged();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }



}
