package com.example.frenge.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.frenge.R;
import com.example.frenge.createquiz.helper.data_adapter;
import com.example.frenge.createquiz.helper.storeHelper;
import com.example.frenge.createquiz.helper.storedata;

import java.util.ArrayList;

public class my_referrals extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView noref,clear;
    ArrayList<storeHelper> dataholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_referrals);

        recyclerView=findViewById(R.id.recyclerv);
        noref=findViewById(R.id.noref);
        clear=findViewById(R.id.clear);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataholder=new ArrayList<>();
        Cursor cursor= new storedata(this).readData();
        while (cursor.moveToNext()){
            storeHelper obj=new storeHelper(cursor.getString(1),cursor.getString(0));
            dataholder.add(obj);

        }
        if (dataholder.size()==0){
            recyclerView.setVisibility(View.INVISIBLE);
            noref.setVisibility(View.VISIBLE);
            clear.setVisibility(View.INVISIBLE);
        }
        data_adapter adapter=new data_adapter(this,dataholder);
        recyclerView.setAdapter(adapter);
        clear.setOnClickListener(view -> {
            storedata storedata=new storedata(this);
            storedata.alldel();
            recyclerView.setVisibility(View.GONE);
            noref.setVisibility(View.VISIBLE);
            clear.setVisibility(View.GONE);
        });
    }
}