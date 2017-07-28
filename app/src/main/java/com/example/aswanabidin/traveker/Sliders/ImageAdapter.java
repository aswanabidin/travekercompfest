package com.example.aswanabidin.traveker.Sliders;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.aswanabidin.traveker.R;

/**
 * Created by aswanabidin on 7/28/17.
 */

public class ImageAdapter extends PagerAdapter {

    Context context;

    ImageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    private int[] sliderImagesId = new int[]{

    };

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageResource(sliderImagesId[i]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }

}
