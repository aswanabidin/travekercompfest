package com.example.aswanabidin.traveker;


import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.FragmentManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment {


    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LayoutTransition lt = new LayoutTransition();
        lt.disableTransitionType(LayoutTransition.DISAPPEARING);
        container.setLayoutTransition(lt);

        return inflater.inflate(R.layout.fragment_booking, container, false);
    }

}
