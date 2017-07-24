package com.example.aswanabidin.traveker.Fragments;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aswanabidin.traveker.HalamanDaftar;
import com.example.aswanabidin.traveker.HalamanFlights;
import com.example.aswanabidin.traveker.HalamanHome;
import com.example.aswanabidin.traveker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;


public class AccountFragment extends Fragment {
    private EditText txtEmail;
    private EditText txtPass;
    private Button btnLogin;
    private FirebaseAuth auth;

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

        txtEmail = (EditText) rootView.findViewById(R.id.etemaillogin);
        txtPass = (EditText) rootView.findViewById(R.id.etpass);
        btnLogin = (Button) rootView.findViewById(R.id.btnlogin);

        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString().trim();
                String pass = txtPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(getContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(getContext(),"gagal",Toast.LENGTH_SHORT).show();
                        }else{
                            //pindahin ke halaman main
                            Intent intent = new Intent(AccountFragment.this.getActivity(), HalamanHome.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        return rootView;
    }

    public void btnRegister(View view) {
        Intent intent = new Intent(AccountFragment.this.getActivity(), HalamanDaftar.class);
        startActivity(intent);
    }


}
