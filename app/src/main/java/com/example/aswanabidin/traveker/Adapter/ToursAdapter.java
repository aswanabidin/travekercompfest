package com.example.aswanabidin.traveker.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.traveker.Model.ListToursModel;
import com.example.aswanabidin.traveker.R;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswanabidin on 7/30/17.
 */

public class ToursAdapter extends RecyclerView.Adapter<ToursAdapter.MyViewHolder>{

    StorageReference mStorageRef;
    OnItemClickListener mItemClickListener;
    private ArrayList<ListToursModel> listToursModels = new ArrayList<>();
    private Activity activity;
    private Context context;

    public ToursAdapter (ArrayList<ListToursModel> listToursModels){
        this.listToursModels = listToursModels;
    }

    public ToursAdapter (Context context) {
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_list_tours, parent, false); //layout recyclerview punya
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ToursAdapter.MyViewHolder holder, int position) {

        final ListToursModel listToursModel = listToursModels.get(position);
        holder.tours.setText(listToursModel.getTour());
        holder.location.setText(listToursModel.getLocation());
        holder.harga.setText(listToursModel.getHarga());
        Picasso.with(context).load(listToursModel.getUrl()).fit().centerCrop().into(holder.imgtours);

        holder.itemTours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, HalamanBookingTours.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listToursModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tours, location, harga;
        private ImageView imgtours;
        private View itemTours;
        ArrayList<ListToursModel> listToursModels = new ArrayList<ListToursModel>();
        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            tours = (TextView) itemView.findViewById(R.id.tvtours);
            location = (TextView) itemView.findViewById(R.id.tvlocation);
            harga = (TextView) itemView.findViewById(R.id.tvharga);
            imgtours = (ImageView) itemView.findViewById(R.id.imgtour);
            itemTours = (View) itemView.findViewById(R.id.viewTours);
        }
    }

    public void  addData(ListToursModel im){
        listToursModels.add(im);
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
