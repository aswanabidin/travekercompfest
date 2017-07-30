package com.example.aswanabidin.traveker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HalamanDaftar extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser user;
    private ProgressDialog progressDialog;

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
        final EditText txtEmail = (EditText) findViewById(R.id.etEmailLP);
        final EditText txtPass = (EditText) findViewById(R.id.etPassword);

        progressDialog = new ProgressDialog(this);

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


                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(HalamanDaftar.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.setMessage("Registering...");
                        progressDialog.show();

                        Toast.makeText(HalamanDaftar.this, "Registering Success, check email for verification", Toast.LENGTH_SHORT).show();

                        if(!task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(HalamanDaftar.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            emailVerification();
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

    public void emailVerification(){
        user = auth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Intent intent = new Intent(HalamanDaftar.this, HalamanHome.class);
                    startActivity(intent);
                    finish();
                }else {
                    Log.e("onCreate : ", "sendEmailVerification", task.getException());
                    Toast.makeText(HalamanDaftar.this,
                            "Failed to send verification email.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
