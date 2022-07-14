package com.example.frenge.howto;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frenge.R;
import com.squareup.picasso.Picasso;

public class whatdash extends AppCompatActivity {
    LinearLayout lnr2,lnr3,lnr4;
    TextView txt,txt2,txt3,txt4;
    ImageView ss1,ss2,ss3,ss4;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtocrt);

        lnr2=findViewById(R.id.lnr2);
        lnr3=findViewById(R.id.lnr3);
        lnr4=findViewById(R.id.lnr4);
        txt=findViewById(R.id.quote);
        txt2=findViewById(R.id.quote2);
        txt3=findViewById(R.id.quote3);
        txt4=findViewById(R.id.quote4);
        ss1=findViewById(R.id.ss);
        ss2=findViewById(R.id.ss2);
        ss3=findViewById(R.id.ss3);
        ss4=findViewById(R.id.ss4);

         String url="https://rb.gy/n6dwzw";
         Picasso.get().load(url).placeholder(R.drawable.lod).into(ss1);
         String text1="For going to your quiz Dashboard you have to Enter That Specific quiz Referral ID into that box and then click on Next If you forget Your Referral id to copy,then go to Your Quiz Referrals option.";
         txt.setText(text1);

         lnr2.setVisibility(View.VISIBLE);
         String url2="https://rb.gy/2vqj6e";
         Picasso.get().load(url2).placeholder(R.drawable.lod).into(ss2);
         String text2="Here You can see your friends names,scores and their Frenge badge with you";
         txt2.setText(text2);
    }
}