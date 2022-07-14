package com.example.frenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class dashborad extends AppCompatActivity {
    ViewPager2 slider;
     LinearLayout layout;
     slider_adapter adapter;
     TextView dot1,dot2,dot3,start,prev;
     ImageView next;
     SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashborad);

        slider=findViewById(R.id.slider);
        layout=findViewById(R.id.dots);
        dot1=findViewById(R.id.txt1);
        dot2=findViewById(R.id.txt2);
        dot3=findViewById(R.id.txt3);
        prev=findViewById(R.id.prev);
        next=findViewById(R.id.next);
        start=findViewById(R.id.redirect);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("session",MODE_PRIVATE);
        String t_f=sharedPreferences.getString("Onboard","");
        if (t_f.equals("Yes")){
            startActivity(new Intent(dashborad.this,MainActivity.class));
            finish();
        }else {
            setitems();
            slider.setAdapter(adapter);
            setdot(0);
            prev.setOnClickListener(view -> {
                startActivity(new Intent(dashborad.this,MainActivity.class));
                finish();
            });
            slider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    setdot(position);
                    nxt(position);
                }
            });
        }
    }

    private  void setitems(){
        List<sliderhelper> sliderhelpers =new ArrayList<>();
        sliderhelper first=new sliderhelper();
        first.setHead( "Test Your Friendship");
        first.setDes("Check the bond of your friendship with your friends.How well do they know you and who is your true BFF.Set a quiz about you and ask them to answer it.Find out Who knows you better and who doesn't. ");
        first.setImage(R.drawable.cr);

        sliderhelper second=new sliderhelper();
        second.setHead("Prove Your Friendship");
        second.setDes("Show Your true bond with your friends and let them know how well you know about them.Join your friends' quizzes and answer them.Get a Friendship Badge and Show your Friends that you are the BFF material. ");
        second.setImage(R.drawable.jn);

        sliderhelper third=new sliderhelper();
        third.setHead("Celebrate Your Friendship");
        third.setDes("You can make Friends easily but BFF is kind of hard thing.So celebrate Your Friendship with Your BFF with Frenge Because \"Best friends are the people in your life who make you laugh louder, smile brighter and live better.\" ");
        third.setImage(R.drawable.cb);

        sliderhelpers.add(first);
        sliderhelpers.add(second);
        sliderhelpers.add(third);
        adapter = new slider_adapter(sliderhelpers);

    }
    public void setdot (int index){
        int child=layout.getChildCount();
        for (int i=0;i<child;i++){
            TextView textView=(TextView) layout.getChildAt(i);
            if (i==index){
                textView.setText(Html.fromHtml("&#8722;"));
                textView.setTextSize(45);
                textView.setTextColor(getResources().getColor(R.color.blue800));
            }else{
                textView.setText(Html.fromHtml("&#8722;"));
                textView.setTextSize(45);
                textView.setTextColor(getResources().getColor(R.color.md_blue_grey_400));
            }
        }
    }
    public void nxt(int index){
        if (index==2){
            start.setVisibility(View.VISIBLE);
            next.setVisibility(View.GONE);
            prev.setVisibility(View.GONE);
            start.setOnClickListener(view -> {
                sharedPreferences=getApplicationContext().getSharedPreferences("session",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Onboard","Yes");
                editor.apply();
                startActivity(new Intent(dashborad.this,MainActivity.class));
                finish();
            });

        }else {
            next.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
            prev.setVisibility(View.VISIBLE);
        }
        next.setOnClickListener(view -> {
            if (index<2){
                int position= index+1;
                setdot(position);
                slider.setCurrentItem(position,true);
            }
        });
    }

}