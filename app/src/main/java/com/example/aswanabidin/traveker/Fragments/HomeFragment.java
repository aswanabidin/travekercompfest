package com.example.aswanabidin.traveker.Fragments;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;

import com.example.aswanabidin.traveker.HalamanFlights;
import com.example.aswanabidin.traveker.HalamanHotel;
import com.example.aswanabidin.traveker.HalamanItenerary;
import com.example.aswanabidin.traveker.HalamanTours;
import com.example.aswanabidin.traveker.R;
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

        final CardView flights = (CardView) rootView.findViewById(R.id.flights) ;

        flights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flights(view);
            }
        });

        final CardView hotels = (CardView) rootView.findViewById(R.id.hotels) ;

        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotels(view);
            }
        });

        final CardView itenerary = (CardView) rootView.findViewById(R.id.itenerary) ;

        itenerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itenerary(view);
            }
        });

        final CardView tours = (CardView) rootView.findViewById(R.id.tours) ;

        tours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tours(view);
            }
        });


        LayoutTransition lt = new LayoutTransition();
        lt.disableTransitionType(LayoutTransition.DISAPPEARING);
        container.setLayoutTransition(lt);

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

    public void flights(View view) {
        Intent intent = new Intent(HomeFragment.this.getActivity(), HalamanFlights.class);
        startActivity(intent);
    }

    public void hotels(View view) {
        Intent intent = new Intent(HomeFragment.this.getActivity(), HalamanHotel.class);
        startActivity(intent);
    }

    public void itenerary(View view) {
        Intent intent = new Intent(HomeFragment.this.getActivity(), HalamanItenerary.class);
        startActivity(intent);
    }

    public void tours(View view) {
        Intent intent = new Intent(HomeFragment.this.getActivity(), HalamanTours.class);
        startActivity(intent);
    }
}
