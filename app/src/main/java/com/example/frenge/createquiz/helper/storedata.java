package com.example.frenge.createquiz.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class storedata extends SQLiteOpenHelper {

    public static final String name="Frenge";
    public static  int num=0;
    public static final String table="Referral";
    public storedata(Context context){
       super(context,name,null,1);
   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String create_table="create Table "+table+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,referral TEXT)";
       sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table);
       onCreate(sqLiteDatabase);
    }

    public  String adddata(String data){
       SQLiteDatabase liteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("referral",data);
       float res= liteDatabase.insert(table,null,contentValues);
       if (res==-1){
          return "failed";
       }else
           return "successful";
    }
    public Cursor readData(){
        SQLiteDatabase liteDatabase=this.getReadableDatabase();
        Cursor cursor=liteDatabase.rawQuery("select * from "+table,null);
        return cursor;
    }
    public Integer deldata(String data){
        SQLiteDatabase liteDatabase=this.getWritableDatabase();
        return liteDatabase.delete(table,"referral=?",new String[]{data});
    }
    public void alldel(){
        SQLiteDatabase liteDatabase=this.getWritableDatabase();
        liteDatabase.execSQL("delete from "+ table);
        liteDatabase.close();
    }
}
