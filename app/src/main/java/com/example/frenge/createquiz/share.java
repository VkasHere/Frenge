package com.example.frenge.createquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.frenge.R;
import com.example.frenge.createquiz.helper.storedata;

import java.util.ArrayList;

public class share extends AppCompatActivity {
    TextView link;
    TextView share,txt;
    ImageView copy,copy1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        link=findViewById(R.id.link);
        share=findViewById(R.id.share);
        txt=findViewById(R.id.txt);
        copy1=findViewById(R.id.copy1);
        copy=findViewById(R.id.copy);
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("link",MODE_PRIVATE);
        String url=sharedPreferences.getString("url","");
        String ref=getIntent().getStringExtra("ref");
        String refe="Your Referral ID is "+ref;
        String res=new storedata(this).adddata(ref);
        txt.setText(refe);
        link.setText(url);
        setclicks(url,ref);
    }

    private void setclicks(String linkk, String refe) {
        String ref=getIntent().getStringExtra("ref");
        String Name=getIntent().getStringExtra("name");
        String text="Let's Know Your Friendship Range with "+Name+"\n\n"+Name+" is inviting you to solve Frenge Quiz to know Friendship Range Between You and "+
                Name+"\n"+"Download Frenge app by clicking Url:"+"\n"+linkk+"\n\n"+Name+"'s Referral code is "+ref+"\n"+"Enter this Referral Code in join quiz option";
        share.setOnClickListener(view -> {
            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.setPackage("com.whatsapp");
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, text);
            try {
                startActivity(whatsappIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "Whatsapp is not installed", Toast.LENGTH_SHORT).show();
            }
        });
        copy.setOnClickListener(view ->{
            ClipboardManager clipboardManager=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData=ClipData.newPlainText("link",linkk);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this, "Link Copied", Toast.LENGTH_SHORT).show();
        } );
        copy1.setOnClickListener(view ->{
            ClipboardManager clipboardManager=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData=ClipData.newPlainText("link",refe);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this, "Referral ID Copied", Toast.LENGTH_SHORT).show();
        } );
    }
}