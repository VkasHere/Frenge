package com.example.frenge.howto;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frenge.R;
import com.squareup.picasso.Picasso;

public class whatref extends AppCompatActivity {
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
        String url="https://rb.gy/pdvzfm" ;
         Picasso.get().load(url).placeholder(R.drawable.lod).into(ss1);
         String text1="Here,You can check all your quizzes' referral ID and Copy Them.Just Click on that Icon and you can clear all by clicking clear button.";
         txt.setText(text1);

    }
}