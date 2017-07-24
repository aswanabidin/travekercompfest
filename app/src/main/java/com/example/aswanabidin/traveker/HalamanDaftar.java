package com.example.aswanabidin.traveker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HalamanDaftar extends AppCompatActivity {
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_daftar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView judul = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        judul.setText("Register");

        Button btnRegis = (Button) findViewById(R.id.btnRegister);
        final EditText txtEmail = (EditText) findViewById(R.id.etEmail);
        final EditText txtPass = (EditText) findViewById(R.id.etPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString().trim();
                String pass = txtPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(HalamanDaftar.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(HalamanDaftar.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                        if(!task.isSuccessful()){
                            Toast.makeText(HalamanDaftar.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            //pindahin ke halaman main
                            Intent intent = new Intent(HalamanDaftar.this, HalamanHome.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }

    // kodingan tombol back <-
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            android.app.FragmentManager fm = getFragmentManager();
            fm.popBackStack();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
