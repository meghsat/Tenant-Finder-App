package com.example.tenantfinder_new;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TenantpropertiesMainActivity extends AppCompatActivity {

    List<List<String>> propertiesdata;
    RecyclerView rvContacts;
    void method2(List<List<String>> lists)
    {
        TenantpropertieshomeActivity adapterobj=new TenantpropertieshomeActivity(lists,"tenantall");
        rvContacts.setAdapter(adapterobj);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_recycler);
         rvContacts = (RecyclerView) findViewById(R.id.rvproperties);
        Intent viewallintent = getIntent();
        String city = viewallintent.getStringExtra("City"); // will return "FirstKeyValue"
        String locality= viewallintent.getStringExtra("locality");
        PropertiesDataActivity.method((city), (locality), new Returnpropertiesdata() {
            @Override
            public void onSuccess(List<List<String>> lists2) {
                propertiesdata = lists2;
                System.out.println(propertiesdata);
                method2(propertiesdata);
            }

        });

    }
}
