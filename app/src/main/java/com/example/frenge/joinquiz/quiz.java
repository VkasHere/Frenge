package com.example.frenge.joinquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.frenge.R;
import com.example.frenge.joinquiz.helper.joinhelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class quiz extends AppCompatActivity implements View.OnClickListener {
    TextView que,quit,next;
    TextView indicator,op1,op2,op3,op4;
    ImageView im1,im2,im3,im4;
    LinearLayout lay1,lay2,lay3,lay4;
    LinearLayout container,back;
    int Score=0;
    MediaPlayer mp,mp1,mp2;
    String img1,img2,img3,img4,num;
    int position=0;
    private Dialog loadingdialog;
    private List<joinhelper> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        indicator= findViewById(R.id.indicator);
        que= findViewById(R.id.ques);
        next= findViewById(R.id.next);
        quit= findViewById(R.id.quit);
        op1=findViewById(R.id.op1);
        op2=findViewById(R.id.op2);
        op3=findViewById(R.id.op3);
        op4=findViewById(R.id.op4);
        im1=findViewById(R.id.img1);
        im2=findViewById(R.id.img2);
        im3=findViewById(R.id.img3);
        im4=findViewById(R.id.img4);
        lay1=findViewById(R.id.lay1);
        lay2=findViewById(R.id.lay2);
        lay3=findViewById(R.id.lay3);
        lay4=findViewById(R.id.lay4);
        back=findViewById(R.id.back);
        mp=MediaPlayer.create(this,R.raw.right);
        mp1=MediaPlayer.create(this,R.raw.wrong);
        mp2=MediaPlayer.create(this,R.raw.sel);

        container=findViewById(R.id.option_container);
        list = new ArrayList<>();

        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.custom_dialoge);
        loadingdialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.dialoge));
        loadingdialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingdialog.setCancelable(false);
        loadingdialog.show();

        String ref=getIntent().getStringExtra("referral");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("joinquiz").child(ref);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    joinhelper test= snapshot1.getValue(joinhelper.class);
                    list.add(test);
                    setquiz(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void setquiz(List<joinhelper> list) {
        loadingdialog.dismiss();
        num= list.get(position).getNum();
        que.setText(list.get(position).getQue());
        img1= list.get(position).getImg1();
        img2= list.get(position).getImg2();
        img3= list.get(position).getImg3();
        img4= list.get(position).getImg4();
        que.setText(list.get(position).getQue());
        op1.setText(list.get(position).getOpp1());
        op2.setText(list.get(position).getOpp2());
        op3.setText(list.get(position).getOpp3());
        op4.setText(list.get(position).getOpp4());
        indicator.setText(position+1+"/"+list.size());
        Picasso.get().load(img1).placeholder(R.drawable.lod).into(im1);
        Picasso.get().load(img2).placeholder(R.drawable.lod).into(im2);
        Picasso.get().load(img3).placeholder(R.drawable.lod).into(im3);
        Picasso.get().load(img4).placeholder(R.drawable.lod).into(im4);
        click();

    }
    private void click() {
        lay1=findViewById(R.id.lay1);
        lay2=findViewById(R.id.lay2);
        lay3=findViewById(R.id.lay3);
        lay4=findViewById(R.id.lay4);
        next= findViewById(R.id.next);
        back= findViewById(R.id.back);
        quit= findViewById(R.id.quit);

        lay1.setOnClickListener(this);
        lay2.setOnClickListener(this);
        lay3.setOnClickListener(this);
        lay4.setOnClickListener(this);
        next.setOnClickListener(this);
        back.setOnClickListener(this);
        quit.setOnClickListener(this);
    }
    private void  enableoption(boolean enable){
       lay1.setEnabled(enable);
       lay2.setEnabled(enable);
       lay3.setEnabled(enable);
       lay4.setEnabled(enable);
        lay1.setBackgroundColor(getResources().getColor(R.color.bluee));
        lay2.setBackgroundColor(getResources().getColor(R.color.bluee));
        lay3.setBackgroundColor(getResources().getColor(R.color.bluee));
        lay4.setBackgroundColor(getResources().getColor(R.color.bluee));
    }

    @Override
    public void onClick(View view) {
        String selop;
        switch (view.getId()) {
            case R.id.lay1:
                enableoption(false);
                next.setEnabled(true);
                next.setAlpha(1);
                selop = op1.getText().toString();
                if (selop.equals(list.get(position).getRight())) {
                    Score++;
                    lay1.setBackgroundColor(getResources().getColor(R.color.green));
                    mp.start();
                    Handler handler=new Handler();
                    handler.postDelayed(() -> {
                        mp.stop();
                        mp.release();
                        mp=MediaPlayer.create(this,R.raw.right);
                    },1000);
                } else {
                    lay1.setBackgroundColor(getResources().getColor(R.color.red));
                    mp1.start();
                    Handler handler=new Handler();
                    handler.postDelayed(() -> {
                        mp1.stop();
                        mp1.release();
                        mp1=MediaPlayer.create(this,R.raw.wrong);
                    },1000);
                  if (op2.getText().toString().equals(list.get(position).getRight())){
                      lay2.setBackgroundColor(getResources().getColor(R.color.green));
                  }else if (op3.getText().toString().equals(list.get(position).getRight())){
                      lay3.setBackgroundColor(getResources().getColor(R.color.green));
                  }else if (op4.getText().toString().equals(list.get(position).getRight()))
                      lay4.setBackgroundColor(getResources().getColor(R.color.green));
                }
                break;
            case R.id.lay2:
                enableoption(false);
                next.setEnabled(true);
                next.setAlpha(1);
                selop = op2.getText().toString();
                if (selop.equals(list.get(position).getRight())) {
                    Score++;
                    lay2.setBackgroundColor(getResources().getColor(R.color.green));
                    mp.start();
                    Handler handler=new Handler();
                    handler.postDelayed(() -> {
                        mp.stop();
                        mp.release();
                        mp=MediaPlayer.create(this,R.raw.right);
                    },1000);
                } else {
                    lay2.setBackgroundColor(getResources().getColor(R.color.red));
                    mp1.start();
                    Handler handler=new Handler();
                    handler.postDelayed(() -> {
                        mp1.stop();
                        mp1.release();
                        mp1=MediaPlayer.create(this,R.raw.wrong);
                    },1000);
                    if (op1.getText().toString().equals(list.get(position).getRight())){
                        lay1.setBackgroundColor(getResources().getColor(R.color.green));
                    }else if (op3.getText().toString().equals(list.get(position).getRight())){
                        lay3.setBackgroundColor(getResources().getColor(R.color.green));
                    }else if (op4.getText().toString().equals(list.get(position).getRight()))
                        lay4.setBackgroundColor(getResources().getColor(R.color.green));
                }
                    break;
                    case R.id.lay3:
                        next.setEnabled(true);
                        next.setAlpha(1);
                        enableoption(false);
                        selop = op3.getText().toString();
                        if (selop.equals(list.get(position).getRight())) {
                            Score++;
                            lay3.setBackgroundColor(getResources().getColor(R.color.green));
                            mp.start();
                            Handler handler=new Handler();
                            handler.postDelayed(() -> {
                                mp.stop();
                                mp.release();
                                mp=MediaPlayer.create(this,R.raw.right);
                            },1000);
                        } else {
                            lay3.setBackgroundColor(getResources().getColor(R.color.red));
                            mp1.start();
                            Handler handler=new Handler();
                            handler.postDelayed(() -> {
                                mp1.stop();
                                mp1.release();
                                mp1=MediaPlayer.create(this,R.raw.wrong);
                            },1000);
                            if (op2.getText().toString().equals(list.get(position).getRight())){
                                lay2.setBackgroundColor(getResources().getColor(R.color.green));
                            }else if (op1.getText().toString().equals(list.get(position).getRight())){
                                lay1.setBackgroundColor(getResources().getColor(R.color.green));
                            }else if (op4.getText().toString().equals(list.get(position).getRight()))
                                lay4.setBackgroundColor(getResources().getColor(R.color.green));
                        }
                        break;
                    case R.id.lay4:
                        next.setEnabled(true);
                        next.setAlpha(1);
                        enableoption(false);
                        selop = op4.getText().toString();
                        if (selop.equals(list.get(position).getRight())) {
                            Score++;
                            lay4.setBackgroundColor(getResources().getColor(R.color.green));
                            mp.start();
                            Handler handler=new Handler();
                            handler.postDelayed(() -> {
                                mp.stop();
                            },1000);
                        } else {
                            lay4.setBackgroundColor(getResources().getColor(R.color.red));
                            mp1.start();
                            Handler handler=new Handler();
                            handler.postDelayed(() -> {
                                mp1.stop();
                            },1000);
                            if (op2.getText().toString().equals(list.get(position).getRight())){
                                lay2.setBackgroundColor(getResources().getColor(R.color.green));
                            }else if (op3.getText().toString().equals(list.get(position).getRight())){
                                lay3.setBackgroundColor(getResources().getColor(R.color.green));
                            }else if (op1.getText().toString().equals(list.get(position).getRight()))
                                lay1.setBackgroundColor(getResources().getColor(R.color.green));
                        }
                        break;
                    case R.id.next:
                        String namee=getIntent().getStringExtra("name");
                        String nam=getIntent().getStringExtra("uid");
                        String ref=getIntent().getStringExtra("referral");
                        next.setEnabled(false);
                        next.setAlpha(0.7f);
                        loadingdialog.show();
                        enableoption(true);
                        position++;
                        if (position == list.size()) {
                            loadingdialog.dismiss();
                            HashMap<String,String> values= new HashMap<String, String>();
                            values.put("name",namee);
                            values.put("score", String.valueOf(Score));
                            values.put("key",nam);
                            DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Dashboard").child(ref);
                            reference.child(nam).setValue(values);
                            int wrong= list.size()-Score;
                            Intent intent = new Intent(quiz.this, scoremeter.class);
                            intent.putExtra("scores",Score);
                            intent.putExtra("wrong",wrong);
                            intent.putExtra("uid",nam);
                            intent.putExtra("ref",ref);
                            startActivity(intent);
                            finish();
                            enableoption(false);
                        } else {
                            setquiz(list);
                        }
                        break;
            case R.id.quit:
                String namee1=getIntent().getStringExtra("name");
                String nam1=getIntent().getStringExtra("uid");
                String ref1=getIntent().getStringExtra("referral");
                HashMap<String,String> values= new HashMap<String, String>();
                values.put("name",namee1);
                values.put("score", String.valueOf(Score));
                values.put("key",nam1);
                DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Dashboard").child(ref1);
                reference.child(nam1).setValue(values);
                Intent intent = new Intent(quiz.this, scoremeter.class);
                intent.putExtra("scores",Score);
                intent.putExtra("uid",nam1);
                intent.putExtra("ref",ref1);
                startActivity(intent);
                finish();
                break;
            case R.id.back:
                quiz.super.onBackPressed();
                finish();
                }


        }
    }


