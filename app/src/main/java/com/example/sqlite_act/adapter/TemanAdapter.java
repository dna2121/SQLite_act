package com.example.sqlite_act.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_act.EditTeman;
import com.example.sqlite_act.LihatTeman;
import com.example.sqlite_act.MainActivity;
import com.example.sqlite_act.R;
import com.example.sqlite_act.database.DBController;
import com.example.sqlite_act.database.Teman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private Context context;

    public TemanAdapter(ArrayList<Teman> listData) {
        this.listData = listData;
    }

    //untuk memanggil tampilan layout adapternya

    @Override
    public TemanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layInf = LayoutInflater.from(parent.getContext());
        View view = layInf.inflate(R.layout.row_data_teman, parent, false);
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TemanViewHolder holder, int position) {
        String nm,tlp;
        DBController controller = new DBController(context);

        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelpon();

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nm);
        holder.telponTxt.setText(tlp);


        //kalo cuma click biasa nampilkan detail data
        holder.cardku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LihatTeman.class);
                intent.putExtra("Nama", nm);
                intent.putExtra("Telpon", tlp);
                context.startActivity(intent);
            }
        });


        //kalo long click muncul menu
        holder.cardku.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                PopupMenu pm = new PopupMenu(context, holder.cardku);
                pm.inflate(R.menu.popupmenu);

                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit:
                                Intent i = new Intent(context, EditTeman.class);
                                i.putExtra("nama",nm);
                                i.putExtra("telpon",tlp);
                                context.startActivity(i);
                                break;
                            case R.id.hapus:
                                HashMap<String,String> qvaluess = new HashMap<>();
                                qvaluess.put("nama",nm);
                                controller.deleteData(qvaluess);
                                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
                pm.show();
                return true;
            }
        });
    }
    //untuk menghitung ukuran array datanya
    @Override
    public int getItemCount() {
        return (listData!= null)? listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namaTxt, telponTxt;

        public TemanViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);
        }
    }
}