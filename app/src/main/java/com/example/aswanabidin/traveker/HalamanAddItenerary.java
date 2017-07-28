package com.example.aswanabidin.traveker;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aswanabidin.traveker.CardHome.HalamanItenerary;
import com.example.aswanabidin.traveker.Model.IteneraryModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class HalamanAddItenerary extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mDatabase;
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private Button btnsubmit, lampirfoto;
    private EditText departureDate, date, location, tourplace, title, description;
    int day, month, year;
    private String userChoosenTask;
    private File files;
    private Uri foto ;
    private String namefile;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageView imfoto;
    private String url;

    public static final String FB_STORAGE_PATH = "image/";
    public static final String FB_DATABASE_PATH = "image";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_add_itenerary);

        // inisialisasi variabel

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("itenerary");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView judul = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        judul.setText("Add Story");

        lampirfoto = (Button) findViewById(R.id.btnAddfoto);
        lampirfoto.setOnClickListener(this);
        imfoto = (ImageView) findViewById(R.id.foto);
        imfoto.setOnClickListener(this);
        imfoto.setAdjustViewBounds(true);

        mDatabase = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btnsubmit = (Button) findViewById(R.id.btnSubmitstory);
        location = (EditText) findViewById(R.id.etStorylocation);
        tourplace = (EditText) findViewById(R.id.etStorytour);
        date = (EditText) findViewById(R.id.etStoryDate);
        title = (EditText) findViewById(R.id.etStorytitle);
        description = (EditText) findViewById(R.id.etStorydescription);


        progressDialog = new ProgressDialog(this);
        departureDate = (EditText) findViewById(R.id.etStoryDate);
        final java.util.Calendar myCalendar = java.util.Calendar.getInstance();

        departureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar mcurrentDate = java.util.Calendar.getInstance();
                year = mcurrentDate.get(java.util.Calendar.YEAR);
                month = mcurrentDate.get(java.util.Calendar.MONTH);
                day = mcurrentDate.get(java.util.Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(HalamanAddItenerary.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        String tanggal;
                        long tanggalpilih = 0;
                        if (selectedmonth < 10) {
                            tanggal = String.valueOf(selectedday + "/" + (++selectedmonth) + "/" + year);
                        } else {
                            tanggal = String.valueOf(selectedday + "/" + (++selectedmonth) + "/" + year);
                        }
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = sdf.parse(tanggal);
                            tanggalpilih = date.getTime();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                            departureDate.setText(tanggal);
                    }
                },year, month, day);
                mDatePicker.setTitle("Departure Date");
                mDatePicker.show();  }
        });

    }

    public void btnSubmitstory(View v){
        if (foto != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            //get the storage reference
            StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() +","+getImageExt(foto));

            //add file to reference
            ref.putFile(foto)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismiss dialog ketika sukses
                            progressDialog.dismiss();

                            //display toast msg ketika sukses
                            Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HalamanItenerary.class));


                            IteneraryModel iteneraryModel = new IteneraryModel(location.getText().toString(), tourplace.getText().toString(),
                                    date.getText().toString(), title.getText().toString(), description.getText().toString(),
                                    taskSnapshot.getDownloadUrl().toString());

                            //save umage info in to firebase database
                            String uploadId = mDatabase.push().getKey();
                            mDatabase.child(uploadId).setValue(iteneraryModel);

                        }

                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            //dismiss dialog ketika terjadi error
                            progressDialog.dismiss();
                            //display toast msg ketika terjadi error
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            //show upload progress
                            double progress = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int)progress+" %");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Please select image" , Toast.LENGTH_SHORT).show();

        }
    }

    // kodingan tombol back <-
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
           Intent intent = new Intent(this, HalamanItenerary.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == lampirfoto) {
            photoBuilder();
        } else if (view == imfoto) {

        }
    }

    @NonNull
    private Boolean isValid() {
        if (imfoto.getDrawable() == null) {
            return false;
        }
        return true;
    }

    private void photoBuilder() {
        final CharSequence[] options = {"Ambil Foto", "Pilih dari Galeri", "Batal"};
        AlertDialog.Builder builder = new AlertDialog.Builder(HalamanAddItenerary.this);
        builder.setTitle("Lampirkan Foto");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(HalamanAddItenerary.this);
                if (options[item].equals("Ambil Foto")) {
                    if (result) {
                        userChoosenTask = "Take Photo";
                        if (result)
                            cameraIntent();
                    }
                } else if (options[item].equals("Pilih dari Galeri")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();
                } else if (options[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //pilih lokasi penyimpanan file
        File file = new File(Environment.getExternalStorageDirectory(), UUID.randomUUID().toString() + ".jpg");
        files = file;
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        namefile = Uri.fromFile(file).getLastPathSegment();
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        } else {
            Toast.makeText(HalamanAddItenerary.this, "Foto gagal diambil, silahkan coba lagi", Toast.LENGTH_LONG).show();
        }
    }

    private void onCaptureImageResult(Intent data) {
        foto = Uri.fromFile(files);
        Picasso.with(HalamanAddItenerary.this).load(files).resize(imfoto.getWidth(), 500).centerCrop().into(imfoto);
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = HalamanAddItenerary.this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            File file = new File(filePath);
            foto = Uri.fromFile(file);
            Picasso.with(HalamanAddItenerary.this).load(file).resize(imfoto.getWidth(), 500).centerCrop().into(imfoto);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Ambil Foto"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Pilih Dari Galeri"))
                        galleryIntent();
                } else {
                    Toast.makeText(this, "Akses Tidak Diizinkan", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public String getImageExt(Uri foto){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(foto));
    }

}
