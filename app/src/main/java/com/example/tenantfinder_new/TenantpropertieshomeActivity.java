package com.example.tenantfinder_new;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class TenantpropertieshomeActivity extends  RecyclerView.Adapter<TenantpropertieshomeActivity.ViewHolderchinnu> {

    List<List<String>> propertiesdata;
    String side;
    FirebaseDatabase database;
    static FirebaseStorage storage;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    int listmainsizetracker;
    public ImageView building1;
    public TextView rent1,description1,address1,owner1;
    TextView viewall;
    ImageButton edit;
    public class ViewHolderchinnu extends RecyclerView.ViewHolder{
        public ImageView building1;
        public EditText rent1,description1,address1,owner1;

        TextView viewall;
        ImageButton edit;

        public ViewHolderchinnu(View tenantview)
        {
            super(tenantview);  // why? (https://stackoverflow.com/questions/68988386/why-do-we-need-to-call-superview-in-viewholder-constructor)see source code ->(https://android.googlesource.com/platform/frameworks/support/+/247185b98675b09c5e98c87448dd24aef4dffc9d/v7/recyclerview/src/android/support/v7/widget/RecyclerView.java#1940), here inside source code in the
            // viewholder of recyclerview there is a parametrized constructor which handles null view exceptions(without this you wont be notified if u are passing a null view), it is not mandatory for
            // class to have a default constructor when there is a parametized constructor but for some libraries like Recyclerview.viewholder they made it mandatory because if its not mandatory u wont be asked for a super keyword without which u cant handle null exception.

            building1=(ImageView) tenantview.findViewById(R.id.building1);
            rent1=(EditText) tenantview.findViewById(R.id.rent1);
            description1=(EditText) tenantview.findViewById(R.id.description1);
            address1=(EditText) tenantview.findViewById(R.id.address1);
            owner1=(EditText) tenantview.findViewById(R.id.owner1);

            viewall =(TextView) tenantview.findViewById(R.id.viewall);
edit=(ImageButton) tenantview.findViewById(R.id.edit);
        }

    }
    public TenantpropertieshomeActivity(List<List<String>> PropertiesDataActivity,String side)
    {

            this.side=side;

        propertiesdata=PropertiesDataActivity;
    }
    @Override
    public ViewHolderchinnu onCreateViewHolder(ViewGroup parent, int viewType)
    // here onCreateViewHoplder is a method which is returning viewHolderobject which is of type ViewHolderchinnu, hence we are writing "ViewHolderchinnu" in above line's onCreateViewholder method creation,
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View tenantview;
        if(side.equals("owner")) {
           tenantview = inflater.inflate(R.layout.activity_ownerinsiderecycler, parent, false);
        }

        else{
            tenantview = inflater.inflate(R.layout.activity_tenant, parent, false);

        }
        // Return a new holder instance
        ViewHolderchinnu viewHolderobject = new ViewHolderchinnu(tenantview);
        return viewHolderobject; // returns the viewHolderobject to adapter which now iteratively calls onBindViewHolder(viewHolderobject,0 to getItemCount() times) getItemCount() times.
    }
    @Override
    public void onBindViewHolder(TenantpropertieshomeActivity.ViewHolderchinnu viewHolderobject, int position) {

            List<String> propertiesdatasub = propertiesdata.get(position);
            if (propertiesdatasub.size() > 0) {
                // Set item views based on your views and data model
                (viewHolderobject.rent1).setText(propertiesdatasub.get(0));
                System.out.println("---"+propertiesdatasub.get(4));
                 StorageReference ref= storage.getInstance().getReference().child((propertiesdatasub.get(4)));
            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    System.out.println(uri);
                    Picasso.get().load(uri).into(viewHolderobject.building1);
                }
            });
                //viewHolderobject.building1.setImageResource(propertiesdatasub.get(4));


                viewHolderobject.description1.setText(propertiesdatasub.get(1));
                viewHolderobject.address1.setText(propertiesdatasub.get(2));

                viewHolderobject.owner1.setText(propertiesdatasub.get(3));
            }
            if(side.equals("owner")) {
                viewHolderobject.edit.setOnClickListener((view) -> {
                    String rentful = "" + viewHolderobject.rent1.getText();
                    String[] rentfularr = rentful.split(" ");
                    String rent = (rentfularr[1]);
                    String[] descriptionfularr = ("" + viewHolderobject.description1.getText()).split(",");
                    String furnished = descriptionfularr[0];
System.out.println(furnished);
System.out.println((descriptionfularr[1]));

                    String bhk = ((descriptionfularr[1]).split(" ")[1]);
                    String type = ((descriptionfularr[1]).split(" ")[3]);
                     System.out.println(bhk);
                     System.out.println();

                    String sqft = ((descriptionfularr[2]).split(" ")[1]);
                    String propertyname = (("" + viewHolderobject.owner1.getText()).split(":"))[1];
                    String address = ("" + viewHolderobject.address1.getText());
                    //  System.out.println(propertyname);
                    //System.out.println(address+" "+sqft);

//System.out.println("boo"+(descriptionfularr[1]).split(" ")[1]);
                    PropertyDataUpdate userdata = new PropertyDataUpdate(address, bhk, "Bangalore", furnished, "HSRLayout", propertyname, rent, sqft, type);
                    System.out.println(userdata);
                    Map<String, Object> updates = new HashMap<String,Object>();
updates.put("address",address);
                    updates.put("bhk",bhk);
                    updates.put("city","Bangalore");
                    updates.put("furnished",furnished);
                    updates.put("locality","HSRLayout");
                    updates.put("propertyname",propertyname);
                    updates.put("rent",rent);
                    updates.put("sqft",sqft);
                    updates.put("type",type);

                    rootNode = FirebaseDatabase.getInstance();

                    reference = rootNode.getReference().child("properties");

                    reference.child("Bangalore/HSRLayout/" + propertiesdatasub.get(5) + "/"+propertyname).updateChildren(updates);
//                            , new DatabaseReference.CompletionListener() {
//                        @Override
//                        public void onComplete(DatabaseError error, DatabaseReference ref) {
//                            System.err.println("Value was set. Error = " + error);
////                    if(error==null)
////                    {
////                        Toast.makeText(UserprofileActivity.this, "Profile Update Successful",Toast.LENGTH_SHORT).show();
////                    }
//                        }
//                    });
                });
            }


    }

    @Override
    public int getItemCount() {
        return propertiesdata.size();
    }
}
