package com.example.tenantfinder_new;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class PropertiesDataActivity  {
    static FirebaseDatabase  rootNode;
    static DatabaseReference reference;
static FirebaseStorage storage;
    public static List<List<String>> method(String city,String locality,final Returnpropertiesdata listener){
        System.out.println(city+" "+locality);
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference().child("properties");
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference().child("properties").child(city).child(locality);
        List<List<String>> lists = new ArrayList<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for(DataSnapshot snapshotproperties:snapshot.getChildren()) {
                        ArrayList<String> list = new ArrayList<>();
                        list.add("Rs " + (snapshotproperties.child("rent").getValue(String.class)).toString() + " / Month");
                        list.add(snapshotproperties.child("furnished").getValue(String.class) + ", " + (snapshotproperties.child("bhk").getValue(String.class)).toString() + " BHK " + snapshotproperties.child("type").getValue(String.class) + ", " + (snapshotproperties.child("sqft").getValue(String.class)).toString() + " sqft.");
                        list.add(snapshotproperties.child("address").getValue(String.class));
                        list.add("Property Name: " + snapshotproperties.child("propertyname").getValue(String.class));
                        list.add("images/"+city+"/"+locality.replaceAll(" ","")+"/"+snapshotproperties.child("propertyname").getValue(String.class));
                        lists.add(list);

                    }
                }
                System.out.println(lists);
                listener.onSuccess(lists);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        return lists;
}
public static void ownerside(String owner,String City, String locality,final Returnpropertiesdata listener){

    rootNode=FirebaseDatabase.getInstance();
    reference=rootNode.getReference().child("properties");
    rootNode=FirebaseDatabase.getInstance();
    reference=rootNode.getReference().child("properties").child(City).child(locality).child(owner);
    System.out.println("--------------------------------");
    List<List<String>> lists = new ArrayList<>();
    reference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                ArrayList<String> list=new ArrayList<>();
                list.add("Rs "+(snapshot.child("rent").getValue(String.class)).toString()+" / Month");
                list.add(snapshot.child("furnished").getValue(String.class)+", "+(snapshot.child("bhk").getValue(String.class)).toString()+" BHK "+snapshot.child("type").getValue(String.class)+", "+(snapshot.child("sqft").getValue(String.class)).toString()+" sqft.");
                list.add(snapshot.child("address").getValue(String.class));
                list.add("Property Name: "+snapshot.child("propertyname").getValue(String.class));
                list.add("images/"+City+"/"+locality+"/"+snapshot.child("propertyname").getValue(String.class));
                list.add(owner);
                lists.add(list);
               // System.out.println(lists);

            }
            //System.out.println(lists);
            listener.onSuccess(lists);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }

    });
  //  System.out.println(lists);


}
}

