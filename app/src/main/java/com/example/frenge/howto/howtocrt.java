package com.example.frenge.howto;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.frenge.R;
import com.squareup.picasso.Picasso;

public class howtocrt extends AppCompatActivity {
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
        String url="https://firebasestorage.googleapis.com/v0/b/frengepvt.appspot.com/o/Extras%2FSs1.jpg?alt=media&token=adfd652e-a202-4802-853b-8c6f7ab4e24f";
         Picasso.get().load(url).placeholder(R.drawable.lod).into(ss1);
         String text1="First,You have to put your name in the box and then select Your Gender Male or Female then click on Next.";
         txt.setText(text1);

         lnr2.setVisibility(View.VISIBLE);
         String url2="https://firebasestorage.googleapis.com/v0/b/frengepvt.appspot.com/o/Extras%2FSs2.jpg?alt=media&token=c131a342-f52b-4120-85f3-4fc2fe9ea368";
         Picasso.get().load(url2).placeholder(R.drawable.lod).into(ss2);
         String text2="Then,Select the category for the quiz which you want to set .";
         txt2.setText(text2);

         lnr3.setVisibility(View.VISIBLE);
         String url3="https://rb.gy/dthesv";
         Picasso.get().load(url3).placeholder(R.drawable.lod).into(ss3);
         String text3="Then,Select Question for your quiz and choose right option.";
         txt3.setText(text3);

         lnr4.setVisibility(View.VISIBLE);
         String url4="https://rb.gy/y4jzut";
         Picasso.get().load(url4).placeholder(R.drawable.lod).into(ss4);
         String text4="Then share the quiz with your friends.Click on share button or you can copy your referral as well as app link and share with your friends.";
         txt4.setText(text4);
    }
}