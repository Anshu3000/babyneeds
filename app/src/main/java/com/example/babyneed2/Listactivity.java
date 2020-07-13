package com.example.babyneed2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.babyneed2.data.Itemclass;
import com.example.babyneed2.model.databasehelper;
import com.example.babyneed2.ui.recycleadapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Listactivity extends AppCompatActivity {
    public RecyclerView r1;
    public recycleadapter rec1;
    public databasehelper da;
    public List<Itemclass> ite1 = new ArrayList<>();
    public FloatingActionButton f1;
    public AlertDialog.Builder albi;
    public AlertDialog aler;
    private EditText item, qty, siz, col;
    private Button sa1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);

        da = new databasehelper(this);
        ite1 = da.que();

        f1 = findViewById(R.id.floatingAction1);

        Log.d("hel", "correct" + ite1);
        r1 = findViewById(R.id.ric12);
        r1.setHasFixedSize(true);
        r1.setLayoutManager(new LinearLayoutManager(this));

        rec1 = new recycleadapter(this, ite1);
        r1.setAdapter(rec1);
        Log.d("hel2", "correct" + ite1);

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creadial1();
            }
        });


    }

    private void creadial1() {

        albi = new AlertDialog.Builder(this);
        View v1 = LayoutInflater.from(this).inflate(R.layout.popup, null);

        item = v1.findViewById(R.id.itemcar);
        qty = v1.findViewById(R.id.qtycared);
        siz = v1.findViewById(R.id.sizecared);
        col = v1.findViewById(R.id.colorcared);
        sa1 = v1.findViewById(R.id.save);

        albi.setView(v1);
        aler = albi.create();
        aler.show();

        sa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Itemclass it1 = new Itemclass();
                it1.setIte(String.valueOf(item.getText()));
                it1.setSiz(Integer.parseInt(String.valueOf(siz.getText())));
                it1.setQty(Integer.parseInt(String.valueOf(qty.getText())));
                it1.setColr(String.valueOf(col.getText()));
                da.inser(it1, v);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        aler.dismiss();
                        Intent in = new Intent(Listactivity.this, Listactivity.class);
                        startActivity(in);
                    }
                }, 1200);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deleteall,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.dele1) {
          da.del();
            Intent in = new Intent(Listactivity.this, Listactivity.class);
            startActivity(in);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
