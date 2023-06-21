package com.example.madfinal;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DonorRegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String[] bloodGroup = {"Enter Blood Group",
            "A+", "A-",
            "B+", "B-",
            "AB+", "AB-", "O+", "O-"};

    EditText name1, dob1, contact1, address1;
    Button register;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_register);

        Spinner spino1 = findViewById(R.id.spinner1);
        spino1.setOnItemSelectedListener(this);
        name1 = (EditText) findViewById(R.id.name1);
        dob1 = (EditText) findViewById(R.id.dob1);
        contact1 = (EditText) findViewById(R.id.contact1);
        address1 = (EditText) findViewById(R.id.address1);
        register = (Button) findViewById(R.id.insert);
//        update = (Button) findViewById(R.id.update);
        dbHelper = new DBHelper(this);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad1
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                bloodGroup);

        register.setOnClickListener(view -> {
            String name1TXT = name1.getText().toString();
            String spino1TXT = spino1.getSelectedItem().toString();
            String dob1TXT = dob1.getText().toString();
            String contact1TXT = contact1.getText().toString();
            String address1TXT = address1.getText().toString();
            if (name1TXT.isEmpty() || dob1TXT.isEmpty() || contact1TXT.isEmpty() || address1TXT.isEmpty() || spino1.getSelectedItemPosition() == 0)
                Toast.makeText(DonorRegisterActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkinsertdata = dbHelper.insertuserdata(name1TXT, spino1TXT, dob1TXT, contact1TXT, address1TXT);
                if (checkinsertdata)
                    Toast.makeText(DonorRegisterActivity.this, "Successfully Registered For Donation", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DonorRegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        });

        // set simple layout resource file
        // for each item of spinner
        ad1.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spino1.setAdapter(ad1);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View arg1, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}