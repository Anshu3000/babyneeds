package com.example.babyneed2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.example.babyneed2.data.Itemclass;
import com.example.babyneed2.util.data1;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class databasehelper extends SQLiteOpenHelper {

    public Context con1;

    public databasehelper(@Nullable Context context ) {
        super(context, data1.database_name, null, data1.data_version);
        this.con1=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(data1.create_table_item);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL(data1.DROP_TABLE);
      onCreate(db);
    }


   public void inser(Itemclass ite, View v){

        ContentValues coin=new ContentValues();
         coin.put(data1.col_name_item,ite.getIte());
         coin.put(data1.col_name_color,ite.getColr());
         coin.put(data1.col_name_qty,ite.getQty());
         coin.put(data1.col_name_size,ite.getSiz());
        SQLiteDatabase db=this.getWritableDatabase();
       Long te= db.insert(data1.table_name,data1.col_name_item,coin);

       if(te!=-1)
       {
           Snackbar.make(v,"data save",Snackbar.LENGTH_SHORT).show();
       }else {
           Toast.makeText(con1,"data not save ",Toast.LENGTH_SHORT).show();
       }

    }

    public  void dele(int id){
        SQLiteDatabase db=this.getWritableDatabase();
         String arg[]={String.valueOf(id)};
        db.delete(data1.table_name,data1.col_name_id+" =? ",arg);
    }

   public void updat(Itemclass ite)
    { SQLiteDatabase db=this.getWritableDatabase();
        ContentValues coin=new ContentValues();
        coin.put(data1.col_name_item,ite.getIte());
        coin.put(data1.col_name_color,ite.getColr());
        coin.put(data1.col_name_qty,ite.getQty());
        coin.put(data1.col_name_size,ite.getSiz());
         String an[]={String.valueOf(ite.getId())};
        long i=db.update(data1.table_name,coin,data1.col_name_id+" =? ",an);

        //if(i!=0)
        {
            Toast.makeText(con1,"update success "+i,Toast.LENGTH_SHORT).show();
        }

    }

    public List<Itemclass> que()
     {
         List<Itemclass> it123=new ArrayList<>();
     SQLiteDatabase db=this.getReadableDatabase();
      String sel[]={data1.col_name_id,data1.col_name_item,data1.col_name_color,data1.col_name_size,data1.col_name_qty};
         Cursor cu=db.query(data1.table_name,sel,null,null,null,null,null);

         while (cu.moveToNext())
         { Itemclass it=new Itemclass();
            it.setId(cu.getInt(cu.getColumnIndex(data1.col_name_id)));
            it.setColr(cu.getString(cu.getColumnIndex(data1.col_name_color)));
            it.setIte(cu.getString(cu.getColumnIndex(data1.col_name_item)));
            it.setQty(cu.getInt(cu.getColumnIndex(data1.col_name_qty)));
            it.setSiz(cu.getInt(cu.getColumnIndex(data1.col_name_size)));
            it123.add(it);
         }
     return it123;
     }


     public Itemclass getitem(int id)
     {    SQLiteDatabase db=this.getReadableDatabase();
         String sel[]={data1.col_name_id,data1.col_name_item,data1.col_name_color,data1.col_name_size,data1.col_name_qty};
         Cursor cu=db.query(data1.table_name,sel,data1.col_name_id+" =? ",new String[]{String.valueOf(id)},null,null,null);
         Itemclass it = new Itemclass();
         while (cu.moveToNext()) {
            // Itemclass it = new Itemclass();
             it.setId(cu.getInt(cu.getColumnIndex(data1.col_name_id)));
             it.setColr(cu.getString(cu.getColumnIndex(data1.col_name_color)));
             it.setIte(cu.getString(cu.getColumnIndex(data1.col_name_item)));
             it.setQty(cu.getInt(cu.getColumnIndex(data1.col_name_qty)));
             it.setSiz(cu.getInt(cu.getColumnIndex(data1.col_name_size)));
         }

         return it;
     }

     public int getcoun()
     { String qu="SELECT * FROM "+data1.table_name;
         SQLiteDatabase db=this.getReadableDatabase();
         Cursor cu=db.rawQuery(qu,null);
         return  cu.getCount();
     }

     public void del()
     { String fe="DELETE FROM "+data1.table_name;
         SQLiteDatabase db=this.getReadableDatabase();
//         db.rawQuery(fe,null);
         db.execSQL("delete from "+ data1.table_name);

         {
             Toast.makeText(con1,"all data are dekete",Toast.LENGTH_SHORT).show();
         }

     }

}
