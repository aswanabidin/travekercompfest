package com.example.aswanabidin.traveker.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.traveker.Model.ItemAirportModel;
import com.example.aswanabidin.traveker.R;

import java.util.ArrayList;

/**
 * Created by aswanabidin on 7/28/17.
 */

public class SpinnerAdapter extends ArrayAdapter<ItemAirportModel> {

    int groupid;
    Activity context;
    ArrayList<ItemAirportModel> list;
    LayoutInflater inflater;

    public SpinnerAdapter(Activity context, int groupid, int id, ArrayList<ItemAirportModel>list){
        super(context,id,list);
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid = groupid;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid,parent,false);
//        ImageView imageView = (ImageView) itemView.findViewById(R.id.img);
        TextView textView = (TextView) itemView.findViewById(R.id.txtspinner);
        textView.setText(list.get(position).getText());
        return itemView;
    }

    public View getDropDownView (int position, View convertView, ViewGroup parent){
        return getView(position,convertView,parent);
    }

}
