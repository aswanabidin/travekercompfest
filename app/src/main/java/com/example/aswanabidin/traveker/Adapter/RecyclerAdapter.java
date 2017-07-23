package com.example.aswanabidin.traveker.Adapter;

import android.support.annotation.IntegerRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.traveker.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.aswanabidin.traveker.R.id.img;
import static com.example.aswanabidin.traveker.R.id.imgitenerary;
import static com.example.aswanabidin.traveker.R.id.title;

/**
 * Created by aswanabidin on 7/22/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String> values;

    public RecyclerAdapter(ArrayList<String> values) {
        this.values = values;
    }



//    private String[] title = {"Chapter One",
//            "Chapter Two",
//            "Chapter Three",
//            "Chapter Four",
//            "Chapter Five",
//            "Chapter Six",
//            "Chapter Seven",
//            "Chapter Eight"};
//
//    private String[] details = {"Item one details",
//            "Item two details", "Item three details",
//            "Item four details", "Item file details",
//            "Item six details", "Item seven details",
//            "Item eight details"};
//
//    private int[] image = { R.drawable.ic_japan,
//            R.drawable.ic_japan,
//            R.drawable.ic_japan,
//            R.drawable.ic_japan,
//            R.drawable.ic_japan,
//            R.drawable.img_slider_berlin,
//            R.drawable.img_slider_berlin,
//            R.drawable.img_slider_berlin };

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_itenerary_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        viewHolder.simage.setImageResource(image[i]);
//        viewHolder.stitle.setText(title[i]);
//        viewHolder.sdetail.setText(details[i]);
        viewHolder.simage.setImageResource(Integer.parseInt(values.get(i)));
        viewHolder.stitle.setText(values.get(i));
        viewHolder.sdetail.setText(values.get(i));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public int currentItem;
        public ImageView simage;
        public TextView stitle;
        public TextView sdetail;

        public ViewHolder(View itemView) {
            super(itemView);
            simage = (ImageView) itemView.findViewById(imgitenerary);
            stitle = (TextView) itemView.findViewById(R.id.titleitenerary);
            sdetail = (TextView) itemView.findViewById(R.id.descriptionitenerary);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    Snackbar.make(v, "Click detected on item " + position,
//                            Snackbar.LENGTH_LONG)
//                            .setAction("Action",null).show();
//                }
//            });
        }
    }

}
