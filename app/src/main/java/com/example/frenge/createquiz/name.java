package com.example.frenge.createquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frenge.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class name extends AppCompatActivity {
    TextInputLayout name;
    TextView next;
    RadioGroup radio;
    RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        name=findViewById(R.id.name);
        next=findViewById(R.id.next);
        radio=findViewById(R.id.Radio);
        next.setOnClickListener(view -> getdata());

    }

    private void getdata() {
        // current date
        Calendar calfordate= Calendar.getInstance();
        SimpleDateFormat currentdate= new SimpleDateFormat("dd");
        final String date = currentdate.format(calfordate.getTime());
        //current time
        Calendar calfortime= Calendar.getInstance();
        SimpleDateFormat currenttime= new SimpleDateFormat("mmss");
        final String time = currenttime.format(calfortime.getTime());
        final String key= date+time;

        final String fullname= name.getEditText().getText().toString().trim();
        final String crtname= name.getEditText().getText().toString();

        if (fullname.isEmpty()){
            name.setError("Please Enter your name");
            name.requestFocus();
        }else {
            if (fullname.length()<4){
                name.setError("Please Enter four or more than four character name");
                name.requestFocus();
            }else{
                name.setError(null);
                name.setErrorEnabled(false);
                int rbid=radio.getCheckedRadioButtonId();
                if (rbid==-1){
                    Toast.makeText(this, "Please Select a Gender", Toast.LENGTH_SHORT).show();
                }else{
                    rb=(RadioButton)findViewById(rbid);
                    String gender=rb.getText().toString();
                    char first= fullname.charAt(0);
                    char sec= fullname.charAt(2);
                    final String id =""+first+sec+key;
                    SharedPreferences preferences=getApplicationContext().getSharedPreferences(id,MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString(id,id);
                    editor.apply();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Referrals");
                    reference.child(id).setValue(crtname);
                    Intent intent = new Intent(name.this, selectquiz.class);
                    intent.putExtra("name1", fullname);
                    intent.putExtra("id1", id);
                    intent.putExtra("gender",gender);
                    startActivity(intent);
                    finish();
                }

            }

        }
    }


}