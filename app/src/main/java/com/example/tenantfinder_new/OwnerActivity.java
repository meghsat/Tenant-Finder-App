package com.example.tenantfinder_new;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OwnerActivity extends AppCompatActivity {
Button addnewproperty;
List<List<String>> propertiesdata;
    RecyclerView rvContacts;
    ImageView userprofile;
    TextView welcomeuser;
    Spinner city,locality;


void method2(List<List<String>> lists)
{
    TenantpropertieshomeActivity adapterobj=new TenantpropertieshomeActivity(lists,"owner");
    rvContacts.setAdapter(adapterobj);
    rvContacts.setLayoutManager(new LinearLayoutManager(this));
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        addnewproperty=(Button) findViewById(R.id.addnewproperty);
        Intent homeintent = getIntent();
        String usernamee = homeintent.getStringExtra("username");
        welcomeuser=(TextView) findViewById(R.id.welcomeuser);
        userprofile=(ImageView) findViewById(R.id.userprofile);
        city = (Spinner) findViewById(R.id.city);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter2);
        locality = (Spinner) findViewById(R.id.locality);
        userprofile.setOnClickListener((view2)->{
            System.out.println("boo");
            Intent userprofileintent=new Intent(OwnerActivity.this,UserprofileActivity.class);
            userprofileintent.putExtra("username",usernamee);
            startActivity(userprofileintent);
        });
        welcomeuser.setText("Welcome "+usernamee);
        addnewproperty.setOnClickListener((view)->
        {

            Intent addproperty=new Intent(OwnerActivity.this,AddNewPropertyActivity.class);
            addproperty.putExtra("username",usernamee);

            startActivity(addproperty);
        });
         rvContacts = (RecyclerView) findViewById(R.id.rvownerproperties);
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
                locality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Context context = adapterView.getContext();
                        PropertiesDataActivity.ownerside(usernamee,(city.getSelectedItem().toString()), (locality.getSelectedItem().toString()), new Returnpropertiesdata() {
                            @Override
                            public void onSuccess(List<List<String>> lists2) {
                                propertiesdata = lists2;
                                System.out.println(propertiesdata);
                                method2(propertiesdata);
                            }

                        });
                    }
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }});
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });



//        TenantpropertieshomeActivity adapterobj=new TenantpropertieshomeActivity(propertiesdata,"owner");
//        rvContacts.setAdapter(adapterobj);
//        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}
