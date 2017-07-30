package com.example.aswanabidin.traveker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aswanabidin.traveker.Utils.BottomNavigationViewHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Created by aswanabidin on 7/25/17.
 */

public class HalamanAccount extends AppCompatActivity {

    private static final String TAG = "HalamanAccount";
    private Context mContext = HalamanAccount.this;
    private static final int ACTIVITY_NUM = 3;
    private EditText txtEmail;
    private EditText txtPass;
    private Button btnLogin;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_account);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        Log.d(TAG, "onCreate: started.");

        setupBottomNavigationView();

        Button btnRegister = (Button) findViewById(R.id.btndaftar) ;

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister(view);
            }
        });

        Button btnImport = (Button) findViewById(R.id.btnimport);
        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnImport(view);
            }
        });

        txtEmail = (EditText) findViewById(R.id.etemaillogin);
        txtPass = (EditText) findViewById(R.id.etpass);
        btnLogin = (Button) findViewById(R.id.btnlogin);

        auth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txtEmail.getText().toString().trim();
                String pass = txtPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(HalamanAccount.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(HalamanAccount.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.setMessage("Login..");
                        progressDialog.show();
                        if(!task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(HalamanAccount.this,"Login Failed!",Toast.LENGTH_SHORT).show();
                        }else{
                            //pindahin ke halaman main
                            Intent intent = new Intent(HalamanAccount.this, HalamanProfile.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        return;
    }

    public void btnRegister(View view) {
        Intent intent = new Intent(HalamanAccount.this, HalamanDaftar.class);
        startActivity(intent);
    }

    public void btnImport(View view) {
        Intent intent1 = new Intent(HalamanAccount.this, HalamanImportSchedule.class);
        startActivity(intent1);
    }

    public void btnImportHotel(View view){
        Intent intent2 = new Intent(HalamanAccount.this, HalamanImportHotel.class);
        startActivity(intent2);
    }

    public void btnImportTours(View view){
        Intent intent3 = new Intent(HalamanAccount.this, HalamanImportTours.class);
        startActivity(intent3);
    }


    /**
     * BottomNavigation setup
     */

    private void setupBottomNavigationView(){
        Log.d(TAG, "setupNottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


    //kodingan tombol keluar beserta transitionnya
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getBaseContext(), HalamanHome.class);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        startActivity(intent);
        finish();
    }

}
