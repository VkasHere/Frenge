package com.example.frenge.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frenge.R;

import java.util.ArrayList;


public class dashadapter extends RecyclerView.Adapter<dashadapter.dashviewholder> {

    private Context context;
    private ArrayList<dashhelper> list;

    public dashadapter(Context context, ArrayList<dashhelper> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public dashviewholder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.leaderboard_recycle, parent, false);
        dashviewholder Dashadapter= new dashviewholder(view);
        return  Dashadapter;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull dashadapter.dashviewholder holder, int position) {
        final dashhelper currentItem = list.get(position);
        holder.Pname.setText(currentItem.getName());
        holder.scr.setText(currentItem.getScore());
        holder.frenge.setText(currentItem.getFrenge());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class dashviewholder extends RecyclerView.ViewHolder {
        TextView Pname,scr,frenge;
        public dashviewholder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            Pname=itemView.findViewById(R.id.Pname);
            scr=itemView.findViewById(R.id.scr);
            frenge=itemView.findViewById(R.id.frenge);
        }
    }
}
