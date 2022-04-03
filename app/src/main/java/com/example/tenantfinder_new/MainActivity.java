package com.example.tenantfinder_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    TextView newuser;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView newuser2 = (TextView) findViewById(R.id.newuser);
        newuser2.setOnClickListener((view) ->
        {
            Intent registerintent=new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(registerintent);
        });
        login=(Button) findViewById(R.id.login);
        newuser=(TextView) findViewById(R.id.newuser);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);

        login.setOnClickListener((view) ->{
            String username2=username.getText().toString().trim();
            String password2=password.getText().toString().trim();
            rootNode=FirebaseDatabase.getInstance();
            reference=rootNode.getReference().child("users");

           reference.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(snapshot.child(username2).exists())
                   {
                       if(password2.equals((snapshot.child(username2).child("password2").getValue(String.class))))
                       {
                           //System.out.println("boo");
                           Intent homeintent=new Intent(MainActivity.this,HomeActivity.class);
                           startActivity(homeintent);
                       }
                       else
                       {
                           Toast.makeText(MainActivity.this, "Password Incorrect",Toast.LENGTH_SHORT).show();
                       }
                   }
                    else{
                       Toast.makeText(MainActivity.this, "User Not Found, Please Register",Toast.LENGTH_SHORT).show();
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
{
    
}
            });
    }
}