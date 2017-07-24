package com.example.aswanabidin.traveker.Adapter;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.traveker.HalamanItenerary;
import com.example.aswanabidin.traveker.Model.Itenerary;
import com.example.aswanabidin.traveker.Model.IteneraryModel;
import com.example.aswanabidin.traveker.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.example.aswanabidin.traveker.R.id.img;
import static com.example.aswanabidin.traveker.R.id.imgitenerary;
import static com.example.aswanabidin.traveker.R.id.title;

/**
 * Created by aswanabidin on 7/22/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

//    private HashMap<String, String> values = null;

    List<IteneraryModel> list;
    Context context;


    public RecyclerAdapter(List<IteneraryModel> list, Context context) {
        this.list = list;
        this.context = context;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        //buat view baru
        View v = LayoutInflater.from(context).inflate(R.layout.rv_itenerary_item, parent, false); //load ke layout cardview
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IteneraryModel mylist = list.get(position);
        holder.location.setText(mylist.getLocation());
        holder.tourplace.setText(mylist.getTourplace());
        holder.date.setText(mylist.getDate());
        holder.title.setText(mylist.getTitle());
        holder.description.setText(mylist.getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, description, location, tourplace, date;


        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.imgitenerary);
            location = (TextView) itemView.findViewById(R.id.locationitenerary);
            tourplace = (TextView) itemView.findViewById(R.id.tourplaceitenerary);
            date = (TextView) itemView.findViewById(R.id.dateitenerary);
            title = (TextView) itemView.findViewById(R.id.titleitenerary);
            description = (TextView) itemView.findViewById(R.id.descriptionitenerary);
        }
    }

}
