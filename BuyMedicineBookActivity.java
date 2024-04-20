package com.divya.healthify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking,cashed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.editTextBMBFullname);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContact);
        edpincode = findViewById(R.id.editTextBMBPincode);
        btnBooking = findViewById(R.id.buttonBMBBooking);
        cashed = findViewById(R.id.cashedMed);

        Intent intent=getIntent();
        String[] p = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String price= p[1];

        Log.e("BUY_MEDICINE ", "price "+p[1] +"--date--"+date+"--");
        cashed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(validate()) {
                   SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                   String username = sharedpreferences.getString("username", "").toString();

                   Database db = new Database(getApplicationContext(), "healthify", null, 1);
                   db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(), Integer.parseInt(edpincode.getText().toString()), date.toString(), "", Float.parseFloat(price.toString()), "medicine");
                   db.removeCart(username, "medicine");
                   Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_LONG).show();
                   startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
               }
               else
               {
                   Toast.makeText(BuyMedicineBookActivity.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
               }

            }
        });
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    Intent it = new Intent(BuyMedicineBookActivity.this, PaymentMod.class);
                    it.putExtra("price", price);
                    it.putExtra("date", date);
                    it.putExtra("edname", edname.getText().toString());
                    it.putExtra("edaddress", edaddress.getText().toString());
                    it.putExtra("edcontact", edcontact.getText().toString());
                    it.putExtra("edpincode", edpincode.getText().toString());
                    Log.e("BUY_MEDICINE ", "price " + p[1] + "--date--" + date + "--");
                    startActivity(it);
                }
                else
                {
                    Toast.makeText(BuyMedicineBookActivity.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
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