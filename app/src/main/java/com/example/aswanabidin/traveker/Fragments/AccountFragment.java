package com.example.aswanabidin.traveker.Fragments;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aswanabidin.traveker.HalamanDaftar;
import com.example.aswanabidin.traveker.HalamanFlights;
import com.example.aswanabidin.traveker.R;


public class AccountFragment extends Fragment {

    public AccountFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutTransition lt = new LayoutTransition();
        lt.disableTransitionType(LayoutTransition.DISAPPEARING);
        container.setLayoutTransition(lt);

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        Button btnRegister = (Button) rootView.findViewById(R.id.btndaftar) ;

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister(view);
            }
        });

        return rootView;
    }

    public void btnRegister(View view) {
        Intent intent = new Intent(AccountFragment.this.getActivity(), HalamanDaftar.class);
        startActivity(intent);
    }


}
