package com.example.frenge.howto;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frenge.R;
import com.squareup.picasso.Picasso;

public class howtojon extends AppCompatActivity {
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

         String url="https://rb.gy/drokmr";
         Picasso.get().load(url).placeholder(R.drawable.lod).into(ss1);
         String text1="For Joining your friend's Quiz, You must enter your name and your friend's Referral ID which He/she shared with you.Then click on Next.";
         txt.setText(text1);

         lnr2.setVisibility(View.VISIBLE);
         String url2="https://rb.gy/p0zwbe";
         Picasso.get().load(url2).placeholder(R.drawable.lod).into(ss2);
         String text2="Now, Read the questions and select a option then click on Next.When You answer all the questions,The Next Button Redirect You to Scoreboard.If you quit the quiz in the middle,It will redirect you to scoreboard.";
         txt2.setText(text2);

         lnr3.setVisibility(View.VISIBLE);
         String url3="https://rb.gy/r5vs4p";
         Picasso.get().load(url3).placeholder(R.drawable.lod).into(ss3);
         String text3="Now you get to know your Friendship Range with Your Friend and you get a Frenge Badge which Shows The bond Between You and Your Friend. Now share this with Your other friends by clicking Share Button.";
         txt3.setText(text3);
    }
}