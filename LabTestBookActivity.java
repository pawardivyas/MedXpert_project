

package com.divya.healthify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking,cashed;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.editTextLTBFullname);
        edaddress = findViewById(R.id.editTextLTBAddress);
        edcontact = findViewById(R.id.editTextLTBContact);
        edpincode = findViewById(R.id.editTextLTBPincode);
        btnBooking = findViewById(R.id.buttonLTBBooking);
        cashed = findViewById(R.id.cashed);
        Intent intent=getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        cashed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()) {
                    SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    String username = sharedpreferences.getString("username", "").toString();

                    Database db = new Database(getApplicationContext(), "healthify", null, 1);
                    db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(), Integer.parseInt(edpincode.getText().toString()), date.toString(), time.toString(), Float.parseFloat(price[1].toString()), "lab");
                    db.removeCart(username, "lab");
                    Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
                }
                else
                {
                    Toast.makeText(LabTestBookActivity.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()) {
                    Intent it = new Intent(LabTestBookActivity.this, LabPaymentMod.class);
                    it.putExtra("price", price[1]);
                    it.putExtra("date", date);
                    it.putExtra("edname", edname.getText().toString());
                    it.putExtra("edaddress", edaddress.getText().toString());
                    it.putExtra("edcontact", edcontact.getText().toString());
                    it.putExtra("edpincode", edpincode.getText().toString());
                    it.putExtra("time", time);
                    Log.e("Lab Test Book Activity ", "price " + price[1] + "--date--" + date + "--");
                    startActivity(it);
                }
                else {
                    Toast.makeText(LabTestBookActivity.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private boolean validate() {
        String p="",n="",a="",c="";

            p=edpincode.getText().toString();
            c = edcontact.getText().toString();

            a= edaddress.getText().toString();
            n = edname.getText().toString();
            if(p.isEmpty()||c.isEmpty()||a.isEmpty()||n.isEmpty())
            {
               return false;
            }
            else
                return true;
    }
}