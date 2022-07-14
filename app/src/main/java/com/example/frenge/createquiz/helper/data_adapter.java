package com.example.frenge.createquiz.helper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frenge.R;
import com.example.frenge.dashboard.dashadapter;
import com.example.frenge.dashboard.dashhelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class data_adapter extends RecyclerView.Adapter<data_adapter.holder>{

    private final Context context;
    private final ArrayList<storeHelper> list;
    public data_adapter(Context context, ArrayList<storeHelper> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @NotNull
    @Override
    public data_adapter.holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.referral_items, parent, false);
        data_adapter.holder Dashadapter= new holder(view);
        return  Dashadapter;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull data_adapter.holder holder, int position) {
        final storeHelper currentItem = list.get(position);
        holder.no.setText(currentItem.getNum());
        holder.refe.setText(currentItem.getRefe());
        holder.copy.setOnClickListener(view -> {
            ClipboardManager clipboardManager=(ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData=ClipData.newPlainText("link",currentItem.getRefe());
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "Referral Code Copied", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class holder extends RecyclerView.ViewHolder {
        TextView no,refe;
        ImageView copy;
        public holder(@NonNull @NotNull View itemView) {
            super(itemView);
            no=itemView.findViewById(R.id.no);
            refe=itemView.findViewById(R.id.refe);
            copy=itemView.findViewById(R.id.copy);
        }
    }
}
