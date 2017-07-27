package com.example.aswanabidin.traveker.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aswanabidin.traveker.CardHome.HalamanItenerary;
import com.example.aswanabidin.traveker.HalamanDetailItenerary;
import com.example.aswanabidin.traveker.Model.IteneraryModel;
import com.example.aswanabidin.traveker.R;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aswanabidin on 7/22/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    //deklarasi variabel
    OnItemClickListener mItemClickListener;
    StorageReference mStorageRef;
    private ArrayList<IteneraryModel> iteneraryModelList = new ArrayList<>();
    private Activity activity;
    private Context context;

    public RecyclerAdapter(ArrayList<IteneraryModel> iteneraryModelList){
        this.iteneraryModelList = iteneraryModelList;
    }

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_itenerary, parent, false); //layout cardview punya
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {

        final IteneraryModel iteneraryModel = iteneraryModelList.get(position);

        holder.location.setText(iteneraryModel.getLocation());
        holder.tourplace.setText(iteneraryModel.getTourplace());
        holder.date.setText(iteneraryModel.getDate());
        holder.title.setText(iteneraryModel.getTitle());
        holder.description.setText(iteneraryModel.getDescription());
        Picasso.with(context).load(iteneraryModel.getUrl()).fit().centerCrop().into(holder.imageView);
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HalamanDetailItenerary.class);
                intent.putExtra("image",iteneraryModel);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //tadi error nihh
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (iteneraryModelList == null) ? 0 : iteneraryModelList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        //viewholder akan mendiskripsikan item view yang ditempatkan di dalam recylcerview

        private TextView location, tourplace, date, title, description;
        private ImageView imageView;
        private CardView cardView;
        private View itemCard;
        ArrayList<IteneraryModel> iteneraryModelList = new ArrayList<IteneraryModel>();
        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);

            location = (TextView) itemView.findViewById(R.id.locationitenerary);
            tourplace = (TextView) itemView.findViewById(R.id.tourplaceitenerary);
            date = (TextView) itemView.findViewById(R.id.dateitenerary);
            title = (TextView) itemView.findViewById(R.id.titleitenerary);
            description = (TextView) itemView.findViewById(R.id.descriptionitenerary);
            imageView = (ImageView) itemView.findViewById(R.id.imgitenerary);
            itemCard = (View) itemView.findViewById(R.id.item_card);

        }
    }

    public void addData(IteneraryModel im) {
        iteneraryModelList.add(im);
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
