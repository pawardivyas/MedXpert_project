package com.divya.healthify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private  String[][] doctor_details1 =
            {
               {"Doctor Name : Kishor khiwsara","Hospital Address : Pimpri", "Exp : 5yrs", "Moble No:9875489859", "600"},
               {"Doctor Name : Aashish Patil","Hospital Address : Dhule", "Exp : 15yrs", "Moble No:8975489859", "900"},
               {"Doctor Name : Sunil Sonawane","Hospital Address : Bhosari", "Exp : 8yrs", "Moble No:8795489859", "300"},
               {"Doctor Name : Sonia Gupta","Hospital Address : Vadgaon", "Exp : 6yrs", "Moble No:9864489859", "500"},
               {"Doctor Name : Kishori Kumari","Hospital Address : Katraj", "Exp : 7yrs", "Moble No:9623479859", "800"}
          };
    private  String[][] doctor_details2 =
            {
                    {"Doctor Name : Neelam Patil","Hospital Address : Pimpri", "Exp : 5yrs", "Moble No:9875489859", "600"},
                    {"Doctor Name : Swati Pawar","Hospital Address : Dhule", "Exp : 15yrs", "Moble No:8975489859", "900"},
                    {"Doctor Name : Vishal Pawar","Hospital Address : Bhosari", "Exp : 8yrs", "Moble No:8795489859", "300"},
                    {"Doctor Name : Sangita Pawar","Hospital Address : Vadgaon", "Exp : 6yrs", "Moble No:9864489859", "500"},
                    {"Doctor Name : Aakash Thorat","Hospital Address : Katraj", "Exp : 7yrs", "Moble No:9623479859", "800"}
            };
    private  String[][] doctor_details3 =
            {
                    {"Doctor Name : Sagar Pawar","Hospital Address : Pimpri", "Exp : 5yrs", "Moble No:9875489859", "200"},
                    {"Doctor Name : Nilima Patil","Hospital Address : Dhule", "Exp : 15yrs", "Moble No:8975489859", "300"},
                    {"Doctor Name : Rahul Sisodiya","Hospital Address : Bhosari", "Exp : 8yrs", "Moble No:8795489859", "300"},
                    {"Doctor Name : Akshay Gupta","Hospital Address : Vadgaon", "Exp : 6yrs", "Moble No:9864489859", "500"},
                    {"Doctor Name : Sonam Kumari","Hospital Address : Katraj", "Exp : 7yrs", "Moble No:9623479859", "600"}
            };
    private  String[][] doctor_details4 =
            {
                    {"Doctor Name : Poonam Mahale","Hospital Address : Pimpri", "Exp : 5yrs", "Moble No:9875489859", "600"},
                    {"Doctor Name : Vaibhavi Kumbhar","Hospital Address : Dhule", "Exp : 15yrs", "Moble No:8975489859", "900"},
                    {"Doctor Name : Pratik Thakur","Hospital Address : Bhosari", "Exp : 8yrs", "Moble No:8795489859", "300"},
                    {"Doctor Name : Akshay More","Hospital Address : Vadgaon", "Exp : 6yrs", "Moble No:9864489859", "500"},
                    {"Doctor Name : Neetu Singh","Hospital Address : Katraj", "Exp : 7yrs", "Moble No:9623479859", "800"}
            };
    private  String[][] doctor_details5 =
            {
                    {"Doctor Name : Pallavi Shah","Hospital Address : Pimpri", "Exp : 5yrs", "Moble No:9875489859", "1600"},
                    {"Doctor Name : Kalpana Rathod","Hospital Address : Dhule", "Exp : 15yrs", "Moble No:8975489859", "1900"},
                    {"Doctor Name : Anand Kelkar","Hospital Address : Bhosari", "Exp : 8yrs", "Moble No:8795489859", "1300"},
                    {"Doctor Name : Vanshika Gupta","Hospital Address : Vadgaon", "Exp : 6yrs", "Moble No:9864489859", "1500"},
                    {"Doctor Name : Sohana Mishra","Hospital Address : Katraj", "Exp : 7yrs", "Moble No:9623479859", "1800"}
            };
    TextView tv;
    Button btn;
    String[][] doctor_details ={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle2);
        btn = findViewById(R.id.buttonLDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dietecian")==0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

         list = new ArrayList();
        for (int i=0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put( "Line1", doctor_details[i][0]);
            item.put( "Line2", doctor_details[i][1]);
            item.put( "Line3", doctor_details[i][2]);
            item.put( "Line4", doctor_details[i][3]);
            item.put( "Line5", "Cons Fees:"+doctor_details[i][4]+"/-");
            list.add( item );
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"Line1","Line2","Line3","Line4","Line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
          );
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}