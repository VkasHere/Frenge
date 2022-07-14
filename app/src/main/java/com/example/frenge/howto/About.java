package com.example.frenge.howto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frenge.R;

public class About extends AppCompatActivity {
    TextView email,whats,upi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        email=findViewById(R.id.email);
        whats=findViewById(R.id.whats);
        upi=findViewById(R.id.upi);

        email.setOnClickListener(view -> {
            sendmail();
        });
        whats.setOnClickListener(view -> {
            whatsapp();
        });
    }

    private void whatsapp() {
        try {
            String num = "+916375700845"; //10 digit number
            Intent intent = new Intent ( Intent.ACTION_VIEW );
            intent.setData ( Uri.parse ( "https://wa.me/" + num + "/?text=" + "Hello,Frenge" ) );
            startActivity ( intent );
        } catch (Exception e) {
            e.printStackTrace ();
        }


    }

    private void sendmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"Vkaspankaj@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback about Frenge");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello,Frenge");
        emailIntent.setType("message/rfc822");
        if (emailIntent.resolveActivity(getPackageManager())!=null){
            startActivity(emailIntent);
        }
    }

}