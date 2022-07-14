package com.example.frenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class slider_adapter extends RecyclerView.Adapter<slider_adapter.viewholder> {
     List<sliderhelper> sliderhelpers;

    public slider_adapter(List<sliderhelper> sliderhelpers) {
        this.sliderhelpers = sliderhelpers;
    }

    @NonNull
    @NotNull
    @Override
    public viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewholder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_layout, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull slider_adapter.viewholder holder, int position) {
        holder.setdata(sliderhelpers.get(position));
    }

    @Override
    public int getItemCount() {
        return sliderhelpers.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        ImageView sliderimg;
        TextView sliderhead;
        TextView sliderdes;

        public viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
             sliderimg=itemView.findViewById(R.id.slide_img);
             sliderhead=itemView.findViewById(R.id.slide_head);
            sliderdes =itemView.findViewById(R.id.slider_text);
        }
        void setdata(sliderhelper sliderhelper){
           sliderimg.setImageResource(sliderhelper.getImage());
           sliderhead.setText(sliderhelper.getHead());
           sliderdes.setText(sliderhelper.getDes());
        }
    }
}
