package com.example.frenge.joinquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.frenge.BuildConfig;
import com.example.frenge.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import pl.droidsonroids.gif.GifImageView;
import pl.pawelkleczkowski.customgauge.CustomGauge;

public class scoremeter extends AppCompatActivity {
    private CustomGauge gauge2;
    int i;
    TextView frnd,quote,share;
    GifImageView gifImageView;
    LinearLayout back;
    MediaPlayer mb;
    String vpoor="Not a Friend",poor="Far Friends",avg="Just Friends",good="Close Friend",vgood="Best Firends",best="The BFFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoremeter);
        gauge2 = findViewById(R.id.gauge2);
        frnd = findViewById(R.id.frnd);
        quote = findViewById(R.id.quote);
        share = findViewById(R.id.share);
        back = findViewById(R.id.back);
        gifImageView=findViewById(R.id.gify);
        String ref=getIntent().getStringExtra("ref");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Referrals");
        mb=MediaPlayer.create(this,R.raw.cele);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.child(ref).exists()){
                    String crtname=snapshot.child(ref).getValue().toString();
                   dataprocess(crtname,ref);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        share.setOnClickListener(view -> {
            verifyStoragePermission(this);
            try {
                createfolder();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        back.setOnClickListener(view -> {
            scoremeter.super.onBackPressed();
            finish();
        });

    }
    public Bitmap getBitmapFromView(View view)
    {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
    private void createfolder() throws FileNotFoundException {
        OutputStream fos;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
            String badge=frnd.getText().toString();
            Date date=new Date();
            CharSequence now=android.text.format.DateFormat.format("yyyyMMddhhmmss",date);
            SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("link",MODE_PRIVATE);
            String url=sharedPreferences.getString("url","");
            String sharetext="Hey, I just Checked my Friendship range and We got "+badge+" Type Frenge Badge \n Let check it out with your friends too \n What Kind of Friendship Range You both have. \n Download Frenge App from Below link and Start Challenging Your Friends \n Frenge Link :- "+url;
            ContentResolver resolver=getContentResolver();
            ContentValues contentValues=new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME,"Fr"+now+".jpg");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE,"image/jpg");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_PICTURES+File.separator+"Frenge");
            Uri uri=resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
            fos=(FileOutputStream)resolver.openOutputStream(Objects.requireNonNull(uri));
            View root=getWindow().getDecorView();
                getBitmapFromView(root).compress(Bitmap.CompressFormat.JPEG,100,fos);
            Objects.requireNonNull(fos);

            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM,uri);
            intent.putExtra(Intent.EXTRA_TEXT,sharetext);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent,"share via"));
        }else {
            Date date=new Date();
            CharSequence now=android.text.format.DateFormat.format("yyyyMMddhhmmss",date);
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Frenge");
            String filename= file+"/"+"Fr"+now+".jpg";
            if (!file.exists()) {
                boolean mkdir = file.mkdir();
            }
            takescreenshot(filename);
        }

    }

    private void takescreenshot(String filename) {

            View root=getWindow().getDecorView();
        root.setDrawingCacheEnabled(true);
        Bitmap bitmap=Bitmap.createBitmap(root.getDrawingCache());
        root.setDrawingCacheEnabled(false);
          try {
            File file1=new File(filename);
            FileOutputStream fileOutputStream= new FileOutputStream(file1);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
              String badge=frnd.getText().toString();
              SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("link",MODE_PRIVATE);
              String url=sharedPreferences.getString("url","");
              String sharetext="Hey, I just Checked my Friendship range and We got "+badge+" Type Frenge Badge \n Let check it out with your friends too \n What Kind of Friendship Range You both have. \n Download Frenge App from Below link and Start Challenging Your Friends \n Frenge Link :- "+url;
              Uri uri= FileProvider.getUriForFile(scoremeter.this, BuildConfig.APPLICATION_ID +".provider",file1);
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM,uri);
            intent.putExtra(Intent.EXTRA_TEXT,sharetext);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent,"share via"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ActivityNotFoundException e){
            Toast.makeText(this,"app not found ",Toast.LENGTH_SHORT).show();
        }

    }

    private void dataprocess(String crtname, String ref) {
        int score=getIntent().getIntExtra("scores",0);
        String nam=getIntent().getStringExtra("uid");
        switch (score){
            case 0:
                score=200;
                break;
            case 1:
                score=250;
                break;
            case 2:
                score=300;
                break;
            case 3:
                score=350;
                break;
            case 4:
                score=400;
                break;
            case 5:
                score=450;
                break;
            case 6:
                score=500;
                break;
            case 7:
                score=550;
                break;
            case 8:
                score=600;
                break;
            case 9:
                score=650;
                break;
            case 10:
                score=700;
                break;
        }
        if (score==200){
            gifImageView.setImageResource(R.drawable.gif4);
            frnd.setText(vpoor);
            String quot="Your Friendship range with "+crtname+" is "+vpoor;
            quote.setText(quot);
        }else if (score<350){
            gifImageView.setImageResource(R.drawable.gif1);
            frnd.setText(poor);
            String quot="Your Friendship range with "+crtname+" is "+poor;
            quote.setText(quot);
        }else if (score<500){
            gifImageView.setImageResource(R.drawable.gif5);
            frnd.setText(avg);
            String quot="Your Friendship range with "+crtname+" is "+avg;
            quote.setText(quot);
        }else if (score<600){
            gifImageView.setImageResource(R.drawable.gif3);
            frnd.setText(good);
            String quot="Your Friendship range with "+crtname+" is "+good;
            quote.setText(quot);
        }else if (score<700){
            gifImageView.setImageResource(R.drawable.gif2);
            frnd.setText(vgood);
            String quot="Your Friendship range with "+crtname+" is "+vgood;
            quote.setText(quot);
        }else{
            gifImageView.setImageResource(R.drawable.gif6);
            frnd.setText(best);
            String quot="Your Friendship range with "+crtname+" is "+best;
            quote.setText(quot);
        }

        int finalScore = score;
        new Thread() {
            public void run() {
                gauge2.setEndValue(700);
                gauge2.setValue(200);
                for (i=0;i<100;i++) {
                    try {
                        runOnUiThread(() -> gauge2.setValue(200+ i*5));
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                    if (gauge2.getValue()== finalScore){
                        break;
                    }
                }
            }
        }.start();
        confetti();
        mb.start();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Dashboard").child(ref);
        String frenge1 =frnd.getText().toString();
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("Frenge",frenge1);
        reference.child(nam).updateChildren(hashMap);
    }

    private void confetti() {
        final KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
        konfettiView.build()
                .addColors(Color.rgb(168,100,253),Color.rgb(41,205,255), Color.rgb(120,255,68),Color.rgb(255,113,141),Color.rgb(253,255,106))
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(1200L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(8, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .streamFor(50, 5000L);

    }

    private static final int REQUEST_EXTERNAL_STOREAGE=1;
    private static final String[] PERMISSION_STORAGE={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermission(Activity activity){
        int permission= ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,PERMISSION_STORAGE,REQUEST_EXTERNAL_STOREAGE);
        }
    }
}