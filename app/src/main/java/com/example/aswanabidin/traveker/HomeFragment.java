package com.example.aswanabidin.traveker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;

import com.example.aswanabidin.traveker.Sliders.FragmentSlider;
import com.example.aswanabidin.traveker.Sliders.SliderIndicator;
import com.example.aswanabidin.traveker.Sliders.SliderPagerAdapter;
import com.example.aswanabidin.traveker.Sliders.SliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        sliderView = (SliderView) rootView.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.pagesContainer);
        setupSlider();
        return rootView;

    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://aswanabidin.files.wordpress.com/2017/07/ic_london.jpg"));
        fragments.add(FragmentSlider.newInstance("https://aswanabidin.files.wordpress.com/2017/07/ic_sydney.jpg"));
        fragments.add(FragmentSlider.newInstance("https://aswanabidin.files.wordpress.com/2017/07/ic_berlin.jpg"));
        fragments.add(FragmentSlider.newInstance("https://aswanabidin.files.wordpress.com/2017/07/ic_paris1.jpg"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

}
