package com.example.tenantfinder_new;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity{
    EditText username,lastname,firstname,password,confirmpassword,emailid,phonenumber;
    TextView existinguser;
    Button register;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        existinguser = (TextView) findViewById(R.id.existinguser);
        existinguser.setOnClickListener((view) ->
        {
            Intent loginintent=new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(loginintent);
        });
        register=(Button) findViewById(R.id.register);
        username=(EditText) findViewById(R.id.username);
        lastname=(EditText) findViewById(R.id.lastname);
        firstname=(EditText) findViewById(R.id.firstname);
        password=(EditText) findViewById(R.id.password);
        confirmpassword=(EditText) findViewById(R.id.confirmpassword);
        emailid=(EditText) findViewById(R.id.emailid);
phonenumber=(EditText)findViewById(R.id.phonenumber);
        register.setOnClickListener((view) ->
        {
            String emailid2 = emailid.getText().toString().trim();
            String username2 = username.getText().toString().trim();
            String confirmpassword2 = confirmpassword.getText().toString().trim();
            String password2 = password.getText().toString().trim();
            String firstname2 = firstname.getText().toString().trim();
            String lastname2 = lastname.getText().toString().trim();
          String phonenumber2 = phonenumber.getText().toString().trim();
            int length =phonenumber2.length();
//            ProgressBar spinner;
//            spinner = (ProgressBar)findViewById(R.id.progressBar);
            if (length == 10)
            {
                if (password2.equals(confirmpassword2)) {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("users");
                    UserDataRegister userdata = new UserDataRegister(emailid2, username2, confirmpassword2, password2, firstname2, lastname2, phonenumber2);
                    // System.out.println(userdata);
                    // System.out.println(reference);
                    reference.child(username2).setValue(userdata, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError error, DatabaseReference ref) {
                            System.err.println("Value was set. Error = " + error);
                            if (error == null) {
                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                Intent loginintent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(loginintent);
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "Passwords Not Matching.", Toast.LENGTH_SHORT).show();

                }
        }
        else {
                Toast.makeText(RegisterActivity.this, "Phone Number should be 10 Digits", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
