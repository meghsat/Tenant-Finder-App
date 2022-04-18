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
//    List<List<String>> lists = new ArrayList<>();
//    ArrayList<String> list=new ArrayList<String>();
//        list.add("Rs 1,00,000 / Month");
//        list.add("Semi Furnished, 1BHK Flat, 1200 sqft.");
//        list.add("Flat no:402, 4th Floor, 5th X roads, infront of Forum Mall, HSR Layout, Bengaluru.");
//        list.add("Owner: Mr. Venkatraya");
//
//        lists.add(list);
//    ArrayList<String> list2=new ArrayList<String>();
//        list2.add("Rs 15,000 / Month");
//        list2.add("Fully Furnished, 3BHK Flat, 2500 sqft.");
//        list2.add("Flat no:500, 5th Floor, RTX cross roads, infront of My friend house, Madhapur, Hyderabad.");
//        list2.add("Owner: Mr. Satya Sai");
//
//        lists.add(list2);
//    ArrayList<String> list3=new ArrayList<String>();
//        list3.add("Rs 1,20,000 / Month");
//        list3.add("Fully Furnished, 6BHK Duplex, 3000 sqft.");
//        list3.add("Door no:76/15/34, Main Road currency naga, infront of lalita jewllers, Nallamalla forest, AP.");
//        list3.add("Owner: Mr. happy");
//
//        lists.add(list3);
//
//        ArrayList<String> list4=new ArrayList<String>();
//        list4.add("Rs 20,000 / Month");
//        list4.add("Not Furnished, 2 BHK Flat, 900 sqft.");
//        list4.add("Flat no:1, First Floor, Mussoorie, infront of starbucks dosas, forest road, Mussoorie.");
//        list4.add("Owner: Mr. Tony Stark");
//
//        lists.add(list4);
//        ArrayList<String> list5=new ArrayList<String>();
//        list5.add("Rs 98,876 / Month");
//        list5.add("Fully Furnished, 5BHK Duplex, 2000 sqft.");
//        list5.add("Door no:1/2/3, FDS nagar, Beside Trendfet Mall, Top of Hill, Delhi.");
//        list5.add("Owner: Mr. Ned Stark");
//
//        lists.add(list5);
//        ArrayList<String> list6=new ArrayList<String>();
//        list6.add("Rs 50,900 / Month");
//        list6.add("Semi Furnished, 3BHK Appartment, 2800 sqft.");
//        list6.add("Door:56-56-78, Beverly Road, Beside tony stark house, jubliee hills, Hyderabad.");
//        list6.add("Owner: Mr. Chris Evans");
//
//        lists.add(list6);
//        ArrayList<String> list7=new ArrayList<String>();
//        list7.add("Rs 10,000 / Week");
//        list7.add("Not Furnished, 1BHK Appartment, 1800 sqft.");
//        list7.add("Flat No:1/3, 4th cross road, Electricity colony, Mega town ship road, inside lake, kerala.");
//        list7.add("Owner: Mr. Ken kaneki");
//
//        lists.add(list7);
//
//        ArrayList<String> list8=new ArrayList<String>();
//        list8.add("Rs 16,000 / Week");
//        list8.add("Semi Furnished, 3BHK Appartment, 700 sqft.");
//        list8.add("Flat No:5/67/89, Waterfall street, Ayyappa nagar town ship, kondareddy burj centre, Chittoor.");
//        list8.add("Owner: Mr. Shoto Todoroki");
//
//        lists.add(list8);
        return lists;
}
public static void ownerside(String owner,final Returnpropertiesdata listener){

    rootNode=FirebaseDatabase.getInstance();
    reference=rootNode.getReference().child("properties");
    rootNode=FirebaseDatabase.getInstance();
    reference=rootNode.getReference().child("properties").child("Bangalore").child("HSRLayout").child(owner);
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
                list.add("images/Bangalore/HSRLayout/"+snapshot.child("propertyname").getValue(String.class));
                list.add(owner);
//                list.add((snapshot.child("rent").getValue(Long.class)).toString());
//                list.add((snapshot.child("sqft").getValue(Long.class)).toString());
//                list.add((snapshot.child("type").getValue(String.class)).toString());
//                list.add(snapshot.child("propertyname").getValue(String.class));
           // StorageReference ref= storage.getInstance().getReference().child("images/Bangalore/HSRLayout/"+snapshot.child("propertyname").getValue(String.class));
//            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    System.out.println(uri);
//                    list.add(String.valueOf(uri));
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    list.add(String.valueOf(e));
//
//                }
//            }) ;
            //   list.add(String.valueOf(storage.getInstance().getReference("images/"+"Bangalore"+"/"+"HSRLayout"+"/"+snapshot.child("propertyname").getValue(String.class))));

//list.add("boo");
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

