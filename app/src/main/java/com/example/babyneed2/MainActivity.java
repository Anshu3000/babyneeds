package com.example.babyneed2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.babyneed2.data.Itemclass;
import com.example.babyneed2.model.databasehelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder alert;
    private AlertDialog dial;
    private EditText item,qty,siz,col;
    private Button sa1;
    private databasehelper datab1;
    private List<Itemclass> lis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        datab1=new databasehelper(this);
        SQLiteDatabase db=datab1.getWritableDatabase();

        bypass();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                creatdial();
            }
        });

    }

    private void bypass() {

         if(datab1.getcoun()>0)
         { Intent in=new Intent(getApplicationContext(),Listactivity.class);
             startActivity(in);
         }
    }

    private void creatdial() {

        alert=new AlertDialog.Builder(this);
        LayoutInflater infl=getLayoutInflater();
        View v1= infl.inflate(R.layout.popup,null);
        item=v1.findViewById(R.id.itemcar);
        qty=v1.findViewById(R.id.qtycared);
        siz=v1.findViewById(R.id.sizecared);
        col=v1.findViewById(R.id.colorcared);
        sa1=v1.findViewById(R.id.save);
        alert.setView(v1);
        dial=alert.create();
        dial.show();

        sa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Itemclass it1=new Itemclass();
                it1.setIte(String.valueOf(item.getText()));
                it1.setSiz(Integer.parseInt(String.valueOf(siz.getText())));
                it1.setQty(Integer.parseInt(String.valueOf(qty.getText())));
                it1.setColr(String.valueOf(col.getText()));
              datab1.inser(it1,v);

              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      dial.dismiss();
                      Intent in=new Intent(getApplicationContext(),Listactivity.class);
                      startActivity(in);
                  }
              },1200);


            }
        });

        lis=datab1.que();




    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
