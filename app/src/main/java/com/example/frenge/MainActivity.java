package com.example.frenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.LinearLayout;
import com.example.frenge.dashboard.my_referrals;
import com.example.frenge.howto.probselect;
import com.example.frenge.joinquiz.referral;
import com.example.frenge.createquiz.name;
import com.example.frenge.dashboard.dashboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
public class MainActivity extends AppCompatActivity {
    LinearLayout container;
    LinearLayout crt,jon,dash,how,hw,About;
    DatabaseReference reference;
    String apk= "1.0.0";
    DownloadManager downloadManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container=findViewById(R.id.container);
        crt=findViewById(R.id.crt);
        jon=findViewById(R.id.jon);
        dash=findViewById(R.id.dash);
        how=findViewById(R.id.how);
        hw=findViewById(R.id.hw);
        About=findViewById(R.id.About);
        getlink();
        //showing check network
        if (!check()) {
            customdialoge();
        }
        //check latest version
        reference= FirebaseDatabase.getInstance().getReference("version");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String ver= snapshot.child("appversion").getValue().toString();
                    if (!apk.equals(ver)){
                        String link=  snapshot.child("link").getValue().toString();
                        dialoge(link);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        verifyStoragePermission(this);
        crt.setOnClickListener(view -> {
            Intent intent= new Intent(MainActivity.this, name.class);
            startActivity(intent);
        });
        jon.setOnClickListener(view -> {
            Intent intent= new Intent(MainActivity.this, referral.class);
            startActivity(intent);
        });
         dash.setOnClickListener(view -> {
        Intent intent= new Intent(MainActivity.this, dashboard.class);
        startActivity(intent);
    });
        how.setOnClickListener(view -> {
            Intent intent= new Intent(MainActivity.this, my_referrals.class);
            startActivity(intent);
        });
        hw.setOnClickListener(view -> {
            Intent intent= new Intent(MainActivity.this, probselect.class);
            startActivity(intent);
        });
        About.setOnClickListener(view -> {
            Intent intent= new Intent(MainActivity.this, com.example.frenge.howto.About.class);
            startActivity(intent);
        });


    }

    private void dialoge(String link) {
        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("You are using old version please download the new version !")
                .setCancelable(false)
                .setPositiveButton("Download" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        download(link);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                        System.exit(0);
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        //download latest version

    }

    private void download(String link) {
        downloadManager =(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri =Uri.parse(link);
        DownloadManager.Request request=new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Frenge.apk");
        Long refrence =downloadManager.enqueue(request);
        MainActivity.super.onBackPressed();
        finish();
    }

    private void getlink() {

       DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("apk");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String linkk=snapshot.child("link").getValue().toString();
                    SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("link",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("url",linkk);
                    editor.apply();

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    //fornetwork check
    private boolean check() {
        boolean have_WIFI = false;
        boolean have_MOBILE = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info : networkInfo) {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    have_WIFI = true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                    have_MOBILE = true;

        }
        return have_WIFI || have_MOBILE;
    }

    //cutom dialoge for network
    private void customdialoge() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Please Connect to a Internet connection to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", (dialogInterface, i) -> {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                })
                .setNegativeButton("Exit", (dialogInterface, i) -> {
                    MainActivity.this.finish();
                    System.exit(0);
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
    //permission
    private static final int REQUEST_EXTERNAL_STOREAGE=1;
    private static final String[] PERMISSION_STORAGE={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermission(Activity activity){
        int permission= ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,PERMISSION_STORAGE,REQUEST_EXTERNAL_STOREAGE);
        }
    }
}