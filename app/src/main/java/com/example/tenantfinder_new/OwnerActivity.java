package com.example.tenantfinder_new;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
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

        PropertiesDataActivity.ownerside(usernamee, new Returnpropertiesdata() {
            @Override
            public  void onSuccess(List<List<String>> lists2){
                propertiesdata=lists2;
                System.out.println(propertiesdata);
       method2(propertiesdata);
            }

        });

//        TenantpropertieshomeActivity adapterobj=new TenantpropertieshomeActivity(propertiesdata,"owner");
//        rvContacts.setAdapter(adapterobj);
//        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}
