package com.example.sqlite_act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqlite_act.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class EditTeman extends AppCompatActivity {
    private TextInputEditText tNama, tTelpon;
    private Button editBtn;
    String nm, tlp,id;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teman);

        tNama = (TextInputEditText) findViewById(R.id.tietNamaBaru);
        tTelpon = (TextInputEditText) findViewById(R.id.tietTelponBaru);
        editBtn = (Button) findViewById(R.id.buttonUpdate);

        controller = new DBController(EditTeman.this);

        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nama");
        tlp = getIntent().getStringExtra("telpon");

        tNama.setText(nm);
        tTelpon.setText(tlp);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tNama.getText().toString().equals("") || tTelpon.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Edit Data Belum Lengkap !", Toast.LENGTH_SHORT).show();
                } else {
                    nm = tNama.getText().toString();
                    tlp = tTelpon.getText().toString();

                    HashMap<String, String> qvalues =  new HashMap<>();
                    qvalues.put("id",id);
                    qvalues.put("nama", nm);
                    qvalues.put("telpon", tlp);

                    controller.updateData(qvalues);

                    Toast.makeText(EditTeman.this, "Data Updated..", Toast.LENGTH_LONG).show();
                    callHome();
                }
            }
        });
    }

    public void callHome() {
        Intent intent = new Intent(EditTeman.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}