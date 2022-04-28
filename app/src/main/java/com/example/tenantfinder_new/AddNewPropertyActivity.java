package com.example.tenantfinder_new;

import android.content.Context;
import android.net.Uri;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tenantfinder_new.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddNewPropertyActivity extends AppCompatActivity{
    ActivityMainBinding binding;
    Spinner city,locality,furnishing;
    Button submit,uploadimages;
    EditText propertyname,address,rent,bhk,sqft;
    String propertyname2,address2,city2,locality2,furnished2,type;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    StorageReference storageReference;
    LinearLayout appartment,commercialcomplex,independenthouse;
    String rent2,bhk2,sqft2;
    String usernamee;
    Uri imageUri;
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
uploadimages=(Button) findViewById(R.id.uploadimages);
        Intent homeintent = getIntent();
         usernamee = homeintent.getStringExtra("username");
uploadimages.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v)
    {
        selectImage();
    }


});

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
                type="CommercialComplex";
            }
        });

        independenthouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="IndependentHouse";
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
            propertyname2=(propertyname.getText().toString()).replaceAll(" ","");
            address2=address.getText().toString();
            rent2=rent.getText().toString();
            bhk2=(bhk.getText().toString());
            sqft2=(sqft.getText().toString());
            city2=city.getSelectedItem().toString();
            locality2=locality.getSelectedItem().toString();
            furnished2=furnishing.getSelectedItem().toString().replaceAll(" ","");
            if (!(imageUri==null||propertyname2.isEmpty() || address2.isEmpty() || rent2.isEmpty() || bhk2.isEmpty() || sqft2.isEmpty() || city2.isEmpty() ||locality2.isEmpty()||furnished2.isEmpty()))
            {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("properties");
                uploadImage();

                GetProperties propertydata = new GetProperties(propertyname2, address2, rent2, bhk2,sqft2,city2,locality2,furnished2,type);
                // System.out.println(userdata);
                // System.out.println(reference);

                reference.child(city2).child(locality2).child(usernamee).child(propertyname2).setValue(propertydata);
                Toast.makeText(AddNewPropertyActivity.this, "Property Added Successfully ", Toast.LENGTH_SHORT).show();

                Intent ownerintent = new Intent(AddNewPropertyActivity.this, OwnerActivity.class);
                startActivity(ownerintent);
            }
             else
            {
                Toast.makeText(AddNewPropertyActivity.this, "None of the fields should be empty", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void uploadImage(){
        String filename=propertyname2;
        System.out.println("===="+imageUri);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+city2+"/"+(locality2).trim()+"/"+(propertyname2).replaceAll(" ",""));
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    private void selectImage() {
    Intent intent=new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(intent,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
       super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==100&& data!=null&&data.getData()!=null)
        {
            imageUri=data.getData();
        }
    }
}
