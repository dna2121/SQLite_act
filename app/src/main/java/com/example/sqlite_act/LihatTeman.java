package com.example.sqlite_act;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sqlite_act.database.DBController;

public class LihatTeman extends AppCompatActivity {
    TextView tvnama, tvtlp;
    String nm, tlp,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_teman);

        tvnama = findViewById(R.id.textviewnama);
        tvtlp = findViewById(R.id.textviewnomor);


        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("Nama");
        tlp = getIntent().getStringExtra("Telpon");

        tvnama.setText(nm);
        tvtlp.setText(tlp);

    }
}