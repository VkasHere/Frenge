package com.example.frenge.howto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.example.frenge.R;

public class probselect extends AppCompatActivity {
    LinearLayout container;
    LinearLayout crt,jon,dash,ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probselect);

        container=findViewById(R.id.container);
        crt=findViewById(R.id.crt);
        jon=findViewById(R.id.jon);
        dash=findViewById(R.id.dash);
        ref=findViewById(R.id.how);
        crt.setOnClickListener(view -> {
            Intent intent= new Intent(probselect.this, howtocrt.class);
            startActivity(intent);
        });
        jon.setOnClickListener(view -> {
            Intent intent= new Intent(probselect.this, howtojon.class);
            startActivity(intent);
        });
        dash.setOnClickListener(view -> {
            Intent intent= new Intent(probselect.this, whatdash.class);
            startActivity(intent);
        });
        ref.setOnClickListener(view -> {
            Intent intent= new Intent(probselect.this, whatref.class);
            startActivity(intent);
        });


    }
}