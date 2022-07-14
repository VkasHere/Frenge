package com.example.frenge.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.frenge.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class leaderboard extends AppCompatActivity {

    RecyclerView recyclerView;
    dashadapter adapter;
    private ArrayList<dashhelper> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        recyclerView=findViewById(R.id.recycler);
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(leaderboard.this));
        recyclerView.setHasFixedSize(true);
        String ref=getIntent().getStringExtra("key");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Dashboard");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(ref)){
                    for (DataSnapshot snapshot1:snapshot.child(ref).getChildren()){
                        dashhelper helper= snapshot1.getValue(dashhelper.class);
                        list.add(helper);
                    }
                    adapter = new dashadapter(leaderboard.this,list);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}