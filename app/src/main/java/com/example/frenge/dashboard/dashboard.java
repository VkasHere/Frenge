package com.example.frenge.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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


public class dashboard extends AppCompatActivity {
    TextInputLayout dashref;
    TextView next;
    dashhelper dashhelper;
    private Dialog loadingdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dashref=findViewById(R.id.dashref);
        next=findViewById(R.id.next);

        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.custom_dialoge);
        loadingdialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialoge));
        loadingdialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingdialog.setCancelable(false);



        next.setOnClickListener(view ->{
            loadingdialog.show();
        if (dashref.getEditText().getText().toString().isEmpty()){
            loadingdialog.dismiss();
            dashref.setError("please Enter referral code");
            dashref.requestFocus();
        }else {
            dashref.setError(null);
            dashref.setErrorEnabled(false);
            getdata();
        }

    });

}

    private void getdata() {
        final String ref= dashref.getEditText().getText().toString();
        SharedPreferences preferences=getApplicationContext().getSharedPreferences(ref,MODE_PRIVATE);
        String uid=preferences.getString(ref,"");
        if (uid.equals(ref)){
            dashhelper = new dashhelper();
            final DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Dashboard");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(ref)){
                        Intent intent= new Intent(dashboard.this,leaderboard.class);
                        intent.putExtra("key",ref);
                        loadingdialog.dismiss();
                        startActivity(intent);
                        finish();

                    }else {
                        loadingdialog.dismiss();
                        dashref.setError("This Referral code does not exist");
                        dashref.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        else {
            loadingdialog.dismiss();
            dashref.setError("This Referral is not yours");
            dashref.requestFocus();
        }
    }


}