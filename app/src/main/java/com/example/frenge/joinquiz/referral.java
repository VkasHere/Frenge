package com.example.frenge.joinquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.frenge.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class referral extends AppCompatActivity {

    TextInputLayout referral,name;
    TextView next;
    private Dialog loadingdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral);
        referral=findViewById(R.id.referral);
        name=findViewById(R.id.name);
        next=findViewById(R.id.next);
        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.custom_dialoge);
        loadingdialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialoge));
        loadingdialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingdialog.setCancelable(false);

        next.setOnClickListener(view -> {
            loadingdialog.show();
            if ( name.getEditText().getText().toString().isEmpty())
            {
                loadingdialog.dismiss();
                name.setError("Please Enter Your name");
                name.requestFocus();
            }
            else {
                name.setError(null);
                name.setErrorEnabled(false);
                if (referral.getEditText().getText().toString().isEmpty()){
                    loadingdialog.dismiss();
                    referral.setError("please Enter referral code to join quiz");
                    referral.requestFocus();
                }else {
                    referral.setError(null);
                    referral.setErrorEnabled(false);
                    getdata();
                }

            }

        });
    }

    private void getdata() {
        final String ref= referral.getEditText().getText().toString();
        final String namee=name.getEditText().getText().toString();
        final String nme=name.getEditText().getText().toString().trim();
        // current date
        Calendar calfordate= Calendar.getInstance();
        SimpleDateFormat currentdate= new SimpleDateFormat("dd");
        final String date = currentdate.format(calfordate.getTime());
        //current time
        Calendar calfortime= Calendar.getInstance();
        SimpleDateFormat currenttime= new SimpleDateFormat("mmss");
        final String time = currenttime.format(calfortime.getTime());
        final String uid= date+time;
        char first= nme.charAt(0);
        char sec= nme.charAt(2);
        final String id =""+first+sec+uid;
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Referrals");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(ref)){
                    referral.setError(null);
                    referral.setErrorEnabled(false);
                        Intent intent = new Intent(com.example.frenge.joinquiz.referral.this, quiz.class);
                        intent.putExtra("referral",ref);
                        intent.putExtra("name",namee);
                        intent.putExtra("uid",id);
                        loadingdialog.dismiss();
                        startActivity(intent);
                        finish();
                }else {
                    loadingdialog.dismiss();
                    referral.setError("This Referral code does not exist");
                    referral.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}