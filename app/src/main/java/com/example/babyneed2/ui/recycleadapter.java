package com.example.babyneed2.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babyneed2.Listactivity;
import com.example.babyneed2.R;
import com.example.babyneed2.data.Itemclass;
import com.example.babyneed2.model.databasehelper;

import java.util.List;

public class recycleadapter extends RecyclerView.Adapter<recycleadapter.viewholder> {
  public   Context con1;
 public List<Itemclass> itemclass;
    public recycleadapter(Context context, List<Itemclass> itemclass) {
        this.con1=context;
        this.itemclass=itemclass;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listlayout1,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Log.d("hel","errror "+itemclass.get(position).getColr());
        Log.d("hel","errror "+itemclass.get(position).getQty());
        Log.d("hel","errror "+itemclass.get(position).getIte());
        Itemclass ite0=itemclass.get(position);
       holder.item.setText(ite0.getIte());
       holder.colo.setText(ite0.getColr());
       holder.qty.setText(String.valueOf(ite0.getQty()));
       holder.siz.setText(String.valueOf(ite0.getSiz()));
    }

    @Override
    public int getItemCount() {
        return itemclass.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView item,siz,colo,qty;
        private ImageView canc,edit;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            item=itemView.findViewById(R.id.carrelitem);
            siz=itemView.findViewById(R.id.carrelsize);
            colo=itemView.findViewById(R.id.carrelcolor);
            qty=itemView.findViewById(R.id.carrelqty);
            canc=itemView.findViewById(R.id.tabimgcancel);
            edit=itemView.findViewById(R.id.tabimgupdate);

             canc.setOnClickListener(this);
             edit.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            final databasehelper db1=new databasehelper(con1);
////              int pos=getAdapterPosition();
//                 Itemclass it = itemclass.get(getAdapterPosition());
//               int  pos=it.getId();
            switch (v.getId())
            {
                case R.id.tabimgcancel :
                    int pos=getAdapterPosition();
                     db1.dele(itemclass.get(pos).getId());
                     itemclass.remove(getAdapterPosition());
                     notifyItemRemoved(getAdapterPosition());
                    break;




                case R.id.tabimgupdate :
                     final Itemclass it1=itemclass.get(getAdapterPosition());
                    final EditText item,qty,siz,col;
                     Button sa1;

                    AlertDialog.Builder bl=new AlertDialog.Builder(con1);
                    final AlertDialog dia;
                     View v1=LayoutInflater.from(con1).inflate(R.layout.popup,null);
                    item=v1.findViewById(R.id.itemcar);
                    qty=v1.findViewById(R.id.qtycared);
                    siz=v1.findViewById(R.id.sizecared);
                    col=v1.findViewById(R.id.colorcared);
                    sa1=v1.findViewById(R.id.save);

                     bl.setView(v1);
                     dia=bl.create();
                     dia.show();

                    item.setText(it1.getIte());
                     qty.setText(String.valueOf(it1.getQty()));
                     siz.setText(String.valueOf(it1.getSiz()));
                     col.setText(it1.getColr());

                     sa1.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             //Itemclass it12=new Itemclass();
                             it1.setIte(String.valueOf(item.getText()));
                             it1.setSiz(Integer.parseInt(String.valueOf(siz.getText())));
                             it1.setQty(Integer.parseInt(String.valueOf(qty.getText())));
                             it1.setColr(String.valueOf(col.getText()));
                             it1.setId(getAdapterPosition());
                             Log.d("col", "onClick:"+it1.getId());
                             db1.updat(it1);
                             dia.dismiss();
                             //itemclass.remove(it1);
                            // db1.dele(it1.getId());
                            // itemclass.remove(getAdapterPosition());
//                             Intent in=new Intent(con1,Listactivity.class);
//                             con1.startActivity(in);
                             notifyItemChanged(getAdapterPosition(),it1);
                         }
                     });

                    break;

            }

        }



    }

}
