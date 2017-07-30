package com.example.aswanabidin.traveker.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.traveker.HalamanDetailHotel;
import com.example.aswanabidin.traveker.Model.ListHotelsModel;
import com.example.aswanabidin.traveker.R;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by aswanabidin on 7/30/17.
 */

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.MyViewHolder>{

    StorageReference mStorageRef;
    OnItemClickListener mItemClickListener;
    private ArrayList<ListHotelsModel> listHotelsModels = new ArrayList<>();
    private Activity activity;
    private Context context;

    public HotelAdapter(ArrayList<ListHotelsModel> listHotelsModels){
        this.listHotelsModels = listHotelsModels;
    }

    public HotelAdapter(Context context){
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_list_hotels, parent, false); //layout recyclerview punya
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotelAdapter.MyViewHolder holder, int position) {

        final ListHotelsModel listHotelsModel = listHotelsModels.get(position);

        holder.nama.setText(listHotelsModel.getNama());
        holder.location.setText(listHotelsModel.getLocation());
        holder.harga.setText(listHotelsModel.getHarga());
        holder.description.setText(listHotelsModel.getDescription());
        Picasso.with(context).load(listHotelsModel.getUrl()).fit().centerCrop().into(holder.imghotel);
        holder.itemHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HalamanDetailHotel.class);
                intent.putExtra("hotels",listHotelsModel);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (listHotelsModels == null) ? 0 : listHotelsModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView nama, location, harga, description;
        private ImageView imghotel;
        private View itemHotels;
        ArrayList<ListHotelsModel> listHotelsModels = new ArrayList<ListHotelsModel>();
        Context context;

        public MyViewHolder(View itemView) {

            super(itemView);

            nama = (TextView) itemView.findViewById(R.id.tvhotels);
            location = (TextView) itemView.findViewById(R.id.tvlocation);
            harga = (TextView) itemView.findViewById(R.id.tvharga);
            description = (TextView) itemView.findViewById(R.id.tvdescription);
            imghotel = (ImageView) itemView.findViewById(R.id.imghotel);
            itemHotels = (View) itemView.findViewById(R.id.viewHotels);
        }
    }

    public void  addData(ListHotelsModel im){
        listHotelsModels.add(im);
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
