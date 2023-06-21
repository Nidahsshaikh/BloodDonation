package com.example.madfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class OrganizerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    Button b12;
    DBHelper DB;

    String[] bloodGroup = {"Select Blood Group",
            "A+", "A-",
            "B+", "B-",
            "AB+", "AB-", "O+", "O-"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Spinner spino1 = findViewById(R.id.bg_spinner);
        spino1.setOnItemSelectedListener(this);
        ArrayAdapter ad1
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                bloodGroup);
        spino1.setAdapter(ad1);
        b12 = findViewById(R.id.b12);
        DB = new DBHelper(this);
        b12.setOnClickListener(view -> {
            Intent intent = new Intent(OrganizerActivity.this, AdminActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        GlobalVariable.bloodGroup = bloodGroup[i];
        Cursor res = DB.getDonorsData();
        if (res.getCount() == 0) {
            Toast.makeText(OrganizerActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Name :" + res.getString(0) + "\n");
            buffer.append("Blood Group:" + res.getString(1) + "\n");
            buffer.append("DOB:" + res.getString(2) + "\n");
            buffer.append("Contact:" + res.getString(3) + "\n");
            buffer.append("Address:" + res.getString(4) + "\n\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(OrganizerActivity.this);
        builder.setCancelable(true);
        builder.setTitle("User Entries");
        builder.setMessage(buffer.toString());
        builder.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}