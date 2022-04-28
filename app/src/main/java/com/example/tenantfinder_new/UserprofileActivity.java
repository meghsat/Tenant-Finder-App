package com.example.tenantfinder_new;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserprofileActivity extends AppCompatActivity {
    EditText username2,firstname2,lastname2,emailid2,password2,phonenumber2;
    TextView welcomeuser2;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button submitchanges,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        Intent userprofileintent = getIntent();
        String usernamee = userprofileintent.getStringExtra("username");
        username2=(EditText) findViewById(R.id.username2);
        firstname2=(EditText) findViewById(R.id.firstname2);
        lastname2=(EditText) findViewById(R.id.lastname2);
        emailid2=(EditText) findViewById(R.id.emailid2);
        password2=(EditText) findViewById(R.id.password2);
        phonenumber2=(EditText) findViewById(R.id.phonenumber2);
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference().child("users");
welcomeuser2=(TextView) findViewById(R.id.welcomeuser2);
welcomeuser2.setText("Welcome "+usernamee);
submitchanges=(Button) findViewById(R.id.submitchanges);
logout =(Button) findViewById(R.id.logout);
logout.setOnClickListener((view)->{
    Intent logoutintent = new Intent(UserprofileActivity.this, MainActivity.class);
    startActivity(logoutintent);
});
submitchanges.setOnClickListener((view)->{
    UserDataRegister userdata=new UserDataRegister(emailid2.getText().toString(),username2.getText().toString(),password2.getText().toString(),password2.getText().toString(),firstname2.getText().toString(),lastname2.getText().toString(),phonenumber2.getText().toString().trim());
    reference.child(usernamee).setValue(userdata,new DatabaseReference.CompletionListener() {
        @Override
        public void onComplete(DatabaseError error, DatabaseReference ref) {
            System.err.println("Value was set. Error = "+error);
            if(error==null)
            {
                Toast.makeText(UserprofileActivity.this, "Profile Update Successful",Toast.LENGTH_SHORT).show();
            }
        }
    });
});
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(usernamee).exists())
                {
                    System.out.println(snapshot);
                    username2.setText(usernamee);

                    firstname2.setText(snapshot.child(usernamee).child("firstname2").getValue(String.class));
                    lastname2.setText(snapshot.child(usernamee).child("lastname2").getValue(String.class));
                    emailid2.setText(snapshot.child(usernamee).child("emailid2").getValue(String.class));
                    password2.setText(snapshot.child(usernamee).child("password2").getValue(String.class));
                    phonenumber2.setText(snapshot.child(usernamee).child("phonenumber2").getValue(String.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
