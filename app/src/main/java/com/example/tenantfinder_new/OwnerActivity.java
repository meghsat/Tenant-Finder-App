package com.example.tenantfinder_new;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OwnerActivity extends AppCompatActivity {
Button addnewproperty;
List<List<String>> propertiesdata;
    RecyclerView rvContacts;
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

        addnewproperty.setOnClickListener((view)->
        {
            Intent addproperty=new Intent(OwnerActivity.this,AddNewPropertyActivity.class);
            startActivity(addproperty);
        });
         rvContacts = (RecyclerView) findViewById(R.id.rvownerproperties);
        PropertiesDataActivity.ownerside("venkat", new Returnpropertiesdata() {
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
