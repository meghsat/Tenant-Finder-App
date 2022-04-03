package com.example.tenantfinder_new;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewPropertyActivity extends AppCompatActivity{
    Spinner city,locality,furnishing;
    Button submit;
    EditText propertyname,address,rent,bhk,sqft;
    String propertyname2,address2,city2,locality2,furnished2,type;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    LinearLayout appartment,commercialcomplex,independenthouse;
    int rent2,bhk2,sqft2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewproperty);

        city = (Spinner) findViewById(R.id.city);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter2);
        locality = (Spinner) findViewById(R.id.locality);


        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = adapterView.getContext();
                if((city.getSelectedItem().toString()).equals("Hyderabad"))
                {
                    ArrayAdapter<CharSequence>   adapter3 = ArrayAdapter.createFromResource(context,
                            R.array.Hyderabad_array, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    locality.setAdapter(adapter3);
                }
                else if((city.getSelectedItem().toString()).equals("Vijayawada"))
                {
                    ArrayAdapter<CharSequence>  adapter3 = ArrayAdapter.createFromResource(context,
                            R.array.vijayawada_array, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    locality.setAdapter(adapter3);
                }
                else if((city.getSelectedItem().toString()).equals("Bangalore"))
                {
                    ArrayAdapter<CharSequence>  adapter3 = ArrayAdapter.createFromResource(context,
                            R.array.Bangalore_array, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    locality.setAdapter(adapter3);
                }
                else if((city.getSelectedItem().toString()).equals("Guntur"))
                {
                    ArrayAdapter<CharSequence>  adapter3 = ArrayAdapter.createFromResource(context,
                            R.array.Guntur_array, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    locality.setAdapter(adapter3);
                }
                else
                {
                    ArrayAdapter<CharSequence>  adapter3 = ArrayAdapter.createFromResource(context,
                            R.array.Guntur_array, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    locality.setAdapter(adapter3);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });



        furnishing = (Spinner) findViewById(R.id.furnishing);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.furnishing_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        furnishing.setAdapter(adapter4);

        appartment=(LinearLayout) findViewById(R.id.appartment);
        commercialcomplex=(LinearLayout) findViewById(R.id.commercialcomplex);
        independenthouse=(LinearLayout) findViewById(R.id.independenthouse);

        appartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Appartment";
            }
        });

        commercialcomplex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="commercial complex";
            }
        });

        independenthouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Independent House";
            }
        });

        propertyname =(EditText) findViewById(R.id.propertyname);
        address=(EditText) findViewById(R.id.address);
        rent=(EditText) findViewById(R.id.rent);
        bhk=(EditText) findViewById(R.id.bhk);
sqft=(EditText) findViewById(R.id.sqft);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener((view)->
        {
            propertyname2=propertyname.getText().toString().trim();
            address2=address.getText().toString().trim();
            rent2=Integer.parseInt(rent.getText().toString().trim());
            bhk2=Integer.parseInt(bhk.getText().toString().trim());
            sqft2=Integer.parseInt(sqft.getText().toString().trim());
            city2=city.getSelectedItem().toString();
            locality2=locality.getSelectedItem().toString();
            furnished2=furnishing.getSelectedItem().toString();
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("properties");
            GetProperties propertydata = new GetProperties(propertyname2, address2, rent2, bhk2,sqft2,city2,locality2,furnished2,type);
            // System.out.println(userdata);
            // System.out.println(reference);
            reference.child(city2).child(locality2).child("venkat").child(propertyname2).setValue(propertydata);
        });
    }
}
