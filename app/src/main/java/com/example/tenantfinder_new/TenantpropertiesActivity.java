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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class TenantpropertiesActivity extends  RecyclerView.Adapter<TenantpropertiesActivity.ViewHolder> {
    FirebaseDatabase database;
    static FirebaseStorage storage;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    List<List<String>> propertiesdata;
    int listmainsizetracker;
public class ViewHolder extends RecyclerView.ViewHolder{
public ImageView building1,building2;
public TextView rent1,description1,address1,owner1,rent2,description2,address2,owner2;
 int listmainsize=propertiesdata.size();

    public ViewHolder(View itemView)
    {
        super(itemView);

    building1=(ImageView) itemView.findViewById(R.id.building1);
     rent1=(TextView) itemView.findViewById(R.id.rent1);
     description1=(TextView) itemView.findViewById(R.id.description1);
     address1=(TextView) itemView.findViewById(R.id.address1);
     owner1=(TextView) itemView.findViewById(R.id.owner1);

        building2=(ImageView) itemView.findViewById(R.id.building2);
        rent2=(TextView) itemView.findViewById(R.id.rent2);
        description2=(TextView) itemView.findViewById(R.id.description2);
        address2=(TextView) itemView.findViewById(R.id.address2);
        owner2=(TextView) itemView.findViewById(R.id.owner2);
    }

}
    public TenantpropertiesActivity(List<List<String>> PropertiesDataActivity)
    {
        System.out.println("---"+PropertiesDataActivity);
        propertiesdata=PropertiesDataActivity;
        listmainsizetracker=propertiesdata.size();
    }
    @Override
    public TenantpropertiesActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.activity_tenantproperties, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(TenantpropertiesActivity.ViewHolder holder, int position) {
    if(listmainsizetracker<=0)
    {
        return;
    }
        // Get the data model based on position
        System.out.println(position+"---");
        System.out.println(propertiesdata.size()+"**");
        if(position>0&&listmainsizetracker>0) {
            if (position == 1 && position + 2 <= holder.listmainsize) {
                position = position + 1;
            } else {
                if ((position * 2) + 1 <= holder.listmainsize) {
                    // System.out.println(position);
                    position = position * 2;

                }
            }
        }
            if(listmainsizetracker>0){
            List<String> propertiesdatasub = propertiesdata.get(position);
            if (propertiesdatasub.size() > 0) {
                // Set item views based on your views and data model
                (holder.rent1).setText(propertiesdatasub.get(0));

               // holder.building1.setImageResource(R.mipmap.download);
                StorageReference ref= storage.getInstance().getReference().child((propertiesdatasub.get(4)).replaceAll(" ",""));
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        System.out.println(uri);
                        Picasso.get().load(uri).into(holder.building1);
                    }
                });

                holder.description1.setText(propertiesdatasub.get(1));
                holder.address1.setText(propertiesdatasub.get(2));

                holder.owner1.setText(propertiesdatasub.get(3));
            }
        }
if(listmainsizetracker>0){
    System.out.println(listmainsizetracker);
            List<String> propertiesdatasub2 = propertiesdata.get(position + 1);
            if (propertiesdatasub2.size() > 0) {
                // Set item views based on your views and data model
                ImageView imageviewbuilding1 = holder.building2;
                TextView rent2 = holder.rent2;
                rent2.setText(propertiesdatasub2.get(0));
                ImageView building12 = holder.building2;
            //    building12.setImageResource(R.mipmap.download2);
                StorageReference ref= storage.getInstance().getReference().child((propertiesdatasub2.get(4)).replaceAll(" ",""));
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        System.out.println(uri);
                        Picasso.get().load(uri).into(building12);
                    }
                });

                TextView rent12 = holder.rent2;
                rent12.setText(propertiesdatasub2.get(0));
                TextView description12 = holder.description2;
                description12.setText(propertiesdatasub2.get(1));
                TextView address12 = holder.address2;
                address12.setText(propertiesdatasub2.get(2));
                TextView owner12 = holder.owner2;
                owner12.setText(propertiesdatasub2.get(3));
                listmainsizetracker--;
            }
        }
    }

    @Override
    public int getItemCount() {
        return propertiesdata.size()/2;
    }
}


//    }
//}