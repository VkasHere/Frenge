package com.example.frenge.createquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frenge.R;
import com.example.frenge.createquiz.helper.helper;
import com.example.frenge.createquiz.helper.sethelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class quizsel extends AppCompatActivity implements View.OnClickListener {
    TextView que;
    TextView indicator,op1,op2,op3,op4;
    ImageView im1,im2,im3,im4;
    LinearLayout lay1,lay2,lay3,lay4,back;
    String img1,img2,img3,img4,num;
    MediaPlayer mp,mp1,mp2;
    private Dialog loadingdialog;
    int position=0;
    private List<sethelper> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizsel);

        indicator= findViewById(R.id.indicator);
        que= findViewById(R.id.ques);
        back= findViewById(R.id.back);
        op1=findViewById(R.id.op1);
        op2=findViewById(R.id.op2);
        op3=findViewById(R.id.op3);
        op4=findViewById(R.id.op4);
        im1=findViewById(R.id.img1);
        im2=findViewById(R.id.img2);
        im3=findViewById(R .id.img3);
        im4=findViewById(R.id.img4);
        lay1=findViewById(R.id.lay1);
        lay2=findViewById(R.id.lay2);
        lay3=findViewById(R.id.lay3);
        lay4=findViewById(R.id.lay4);
        list = new ArrayList<>();
        mp=MediaPlayer.create(this,R.raw.right);
        mp1=MediaPlayer.create(this,R.raw.wrong);
        mp2=MediaPlayer.create(this,R.raw.sel);

        back.setOnClickListener(view -> {
            quizsel.super.onBackPressed();
            finish();
        });
        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.custom_dialoge);
        loadingdialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialoge));
        loadingdialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingdialog.setCancelable(false);
        loadingdialog.show();
        String gender=getIntent().getStringExtra("gender");
        String quiz=getIntent().getStringExtra("quiz");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("quizes").child(quiz).child(gender);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    sethelper test= snapshot1.getValue(sethelper.class);
                    list.add(test);
                   setquiz(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        back.setOnClickListener(view -> {
            quizsel.super.onBackPressed();
            finish();
        });

    }

    private void setquiz(List<sethelper> list) {
        loadingdialog.dismiss();
        if (list.size()!=0){
            num= list.get(position).getNum();
            que.setText(list.get(position).getQue());
            img1= list.get(position).getImg1();
            img2= list.get(position).getImg2();
            img3= list.get(position).getImg3();
            img4= list.get(position).getImg4();
            que.setText(list.get(position).getQue());
            op1.setText(list.get(position).getOp1());
            op2.setText(list.get(position).getOp2());
            op3.setText(list.get(position).getOp3());
            op4.setText(list.get(position).getOp4());
            indicator.setText(position+1+"/"+list.size());
            Picasso.get().load(img1).placeholder(R.drawable.lod).into(im1);
            Picasso.get().load(img2).placeholder(R.drawable.lod).into(im2);
            Picasso.get().load(img3).placeholder(R.drawable.lod).into(im3);
            Picasso.get().load(img4).placeholder(R.drawable.lod).into(im4);
            click();
        }else
            Toast.makeText(this, "No Quiz Available !", Toast.LENGTH_SHORT).show();


    }

    private void click() {
        lay1=findViewById(R.id.lay1);
        lay2=findViewById(R.id.lay2);
        lay3=findViewById(R.id.lay3);
        lay4=findViewById(R.id.lay4);

        lay1.setOnClickListener(this);
        lay2.setOnClickListener(this);
        lay3.setOnClickListener(this);
        lay4.setOnClickListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        String name=getIntent().getStringExtra("name1");
        String id=getIntent().getStringExtra("id1");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("joinquiz").child(id);
        String right,opp1,opp2,opp3,opp4;
        helper quizhel;
       switch (view.getId()){
           case R.id.lay1:
               mp2.start();
               Handler handler=new Handler();
               handler.postDelayed(() -> {
                   mp2.stop();
                   mp.release();
                   mp2=MediaPlayer.create(this,R.raw.sel);
               },300);
                right= op1.getText().toString();
               img1= list.get(position).getImg1();
               img2= list.get(position).getImg2();
               img3= list.get(position).getImg3();
               img4= list.get(position).getImg4();
                opp1 =op1.getText().toString();
                opp2 =op2.getText().toString();
                opp3 =op3.getText().toString();
                opp4 =op4.getText().toString();

                quizhel= new helper(img1,img2,img3,img4,opp1,opp2,opp3,opp4,num,que.getText().toString(),right);
               reference.child(num).setValue(quizhel);
              position++;
              if (position==list.size()){
                  DatabaseReference r1=FirebaseDatabase.getInstance().getReference().child("Dashboard").child(id);
                  r1.setValue("");
                  Intent intent = new Intent(this, share.class);
                  intent.putExtra("ref",id);
                  intent.putExtra("name",name);
                  startActivity(intent);
                  finish();
              }else {
                  setquiz(list);
              }
              break;
           case R.id.lay2:
               mp2.start();
               Handler handler2=new Handler();
               handler2.postDelayed(() -> {
                   mp2.stop();
                   mp2.release();
                   mp2=MediaPlayer.create(this,R.raw.sel);
               },300);
                right= op2.getText().toString();
               img1= list.get(position).getImg1();
               img2= list.get(position).getImg2();
               img3= list.get(position).getImg3();
               img4= list.get(position).getImg4();
                opp1 =op1.getText().toString();
                opp2 =op2.getText().toString();
                opp3 =op3.getText().toString();
                opp4 =op4.getText().toString();

                quizhel= new helper(img1,img2,img3,img4,opp1,opp2,opp3,opp4,num,que.getText().toString(),right);
               reference.child(num).setValue(quizhel);
               position++;
               if (position==list.size()){
                   DatabaseReference r1=FirebaseDatabase.getInstance().getReference().child("Dashboard").child(id);
                   r1.setValue("");
                   Intent intent = new Intent(this, share.class);
                   intent.putExtra("ref",id);
                   intent.putExtra("name",name);
                   startActivity(intent);
                   finish();
               }else {
                   setquiz(list);
               }
               break;
           case R.id.lay3:
               mp2.start();
               Handler handler3=new Handler();
               handler3.postDelayed(() -> {
                   mp2.stop();
                   mp2.release();
                   mp2=MediaPlayer.create(this,R.raw.sel);
               },300);
               right= op3.getText().toString();
               img1= list.get(position).getImg1();
               img2= list.get(position).getImg2();
               img3= list.get(position).getImg3();
               img4= list.get(position).getImg4();
               opp1 =op1.getText().toString();
               opp2 =op2.getText().toString();
               opp3 =op3.getText().toString();
               opp4 =op4.getText().toString();

               quizhel= new helper(img1,img2,img3,img4,opp1,opp2,opp3,opp4,num,que.getText().toString(),right);
               reference.child(num).setValue(quizhel);
               position++;
               if (position==list.size()){
                   DatabaseReference r1=FirebaseDatabase.getInstance().getReference().child("Dashboard").child(id);
                   r1.setValue("");
                   Intent intent = new Intent(this, share.class);
                   intent.putExtra("ref",id);
                   intent.putExtra("name",name);
                   startActivity(intent);
                   finish();
               }else {
                   setquiz(list);
               }
               break;
           case R.id.lay4:
               mp2.start();
               Handler handler4=new Handler();
               handler4.postDelayed(() -> {
                   mp2.stop();
                   mp2.release();
                   mp2=MediaPlayer.create(this,R.raw.sel);
               },300);
               right= op4.getText().toString();
               img1= list.get(position).getImg1();
               img2= list.get(position).getImg2();
               img3= list.get(position).getImg3();
               img4= list.get(position).getImg4();
               opp1 =op1.getText().toString();
               opp2 =op2.getText().toString();
               opp3 =op3.getText().toString();
               opp4 =op4.getText().toString();

               quizhel= new helper(img1,img2,img3,img4,opp1,opp2,opp3,opp4,num,que.getText().toString(),right);
               reference.child(num).setValue(quizhel);
               position++;
               if (position==list.size()){
                   DatabaseReference r1=FirebaseDatabase.getInstance().getReference().child("Dashboard").child(id);
                   r1.setValue("");
                   Intent intent = new Intent(this, share.class);
                   intent.putExtra("ref",id);
                   intent.putExtra("name",name);
                   startActivity(intent);
                   finish();
               }else {
                   setquiz(list);
               }

       }
    }
}