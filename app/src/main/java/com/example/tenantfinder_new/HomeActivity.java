package com.example.tenantfinder_new;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    Button buttontenant, buttonowner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttontenant=(Button) findViewById(R.id.buttontenant);
        buttonowner=(Button) findViewById(R.id.buttonowner);

        buttontenant.setOnClickListener((view) ->
        {
            Intent homeintent = getIntent();
            Intent tenantintent=new Intent(HomeActivity.this,TenantActivity.class);
            tenantintent.putExtra("username",homeintent.getStringExtra("username"));
            startActivity(tenantintent);
        });

                buttonowner.setOnClickListener((view) ->
                {
                    Intent homeintent = getIntent();
                    Intent ownerintent = new Intent(HomeActivity.this, OwnerActivity.class);
                    ownerintent.putExtra("username",homeintent.getStringExtra("username"));

                    startActivity(ownerintent);
                });
    }
}
