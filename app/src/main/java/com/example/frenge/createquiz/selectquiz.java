package com.example.frenge.createquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.frenge.R;
import com.squareup.picasso.Picasso;

public class selectquiz extends AppCompatActivity {
    LinearLayout lay1,lay2,lay3;
    ImageView img1,img2,img3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectquiz);
        lay1=findViewById(R.id.lay1);
        lay2=findViewById(R.id.lay2);
        lay3=findViewById(R.id.lay3);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        String fullname=getIntent().getStringExtra("name1");
        String id=getIntent().getStringExtra("id1");
        String gender=getIntent().getStringExtra("gender");
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/frengepvt.appspot.com/o/Extras%2Fl2.png?alt=media&token=d7afe0f8-3b70-43b2-a698-5a4ddc8be02c").placeholder(R.drawable.lod).into(img1);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/frengepvt.appspot.com/o/Extras%2Fl3.png?alt=media&token=badc40bc-3d24-47e0-8723-fc7f2fd05910").placeholder(R.drawable.lod).into(img2);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/frengepvt.appspot.com/o/Extras%2Fl1.png?alt=media&token=7da3a53f-e8c0-401b-809d-ba4cb9c5b752").placeholder(R.drawable.lod).into(img3);
        lay1.setOnClickListener(view -> {
            Intent intent= new Intent(selectquiz.this,quizsel.class);
            intent.putExtra("quiz","Like");
            intent.putExtra("name1", fullname);
            intent.putExtra("id1", id);
            intent.putExtra("gender",gender);
            startActivity(intent);
            finish();
        });
        lay2.setOnClickListener(view -> {
            Intent intent= new Intent(selectquiz.this,quizsel.class);
            intent.putExtra("quiz","Habitual");
            intent.putExtra("name1", fullname);
            intent.putExtra("id1", id);
            intent.putExtra("gender",gender);
            startActivity(intent);
            finish();
        });
        lay3.setOnClickListener(view -> {
            Intent intent= new Intent(selectquiz.this,quizsel.class);
            intent.putExtra("quiz","Decision");
            intent.putExtra("name1", fullname);
            intent.putExtra("id1", id);
            intent.putExtra("gender",gender);
            startActivity(intent);
            finish();
        });
    }
}