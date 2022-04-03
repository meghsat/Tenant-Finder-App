package com.example.tenantfinder_new;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
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

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class TenantpropertieshomeActivity extends  RecyclerView.Adapter<TenantpropertieshomeActivity.ViewHolderchinnu> {

    List<List<String>> propertiesdata;
    String side;
    int listmainsizetracker;
    public class ViewHolderchinnu extends RecyclerView.ViewHolder{
        public ImageView building1;
        public TextView rent1,description1,address1,owner1;
        TextView viewall;

        public ViewHolderchinnu(View tenantview)
        {
            super(tenantview);  // why? (https://stackoverflow.com/questions/68988386/why-do-we-need-to-call-superview-in-viewholder-constructor)see source code ->(https://android.googlesource.com/platform/frameworks/support/+/247185b98675b09c5e98c87448dd24aef4dffc9d/v7/recyclerview/src/android/support/v7/widget/RecyclerView.java#1940), here inside source code in the
            // viewholder of recyclerview there is a parametrized constructor which handles null view exceptions(without this you wont be notified if u are passing a null view), it is not mandatory for
            // class to have a default constructor when there is a parametized constructor but for some libraries like Recyclerview.viewholder they made it mandatory because if its not mandatory u wont be asked for a super keyword without which u cant handle null exception.

            building1=(ImageView) tenantview.findViewById(R.id.building1);
            rent1=(TextView) tenantview.findViewById(R.id.rent1);
            description1=(TextView) tenantview.findViewById(R.id.description1);
            address1=(TextView) tenantview.findViewById(R.id.address1);
            owner1=(TextView) tenantview.findViewById(R.id.owner1);

            viewall =(TextView) tenantview.findViewById(R.id.viewall);

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
        else if(side.equals("tenantall"))
        {
            tenantview = inflater.inflate(R.layout.activity_tenantproperties, parent, false);

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

        // Get the data model based on position

//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(holder.mContext,
//                R.array.planets_array, android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        holder.spinner.setAdapter(adapter2);
//        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(holder.mContext,
//                R.array.planets_array, android.R.layout.simple_spinner_item);
//        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        holder.spinner2.setAdapter(adapter3);
//        holder.viewall.setOnClickListener((view)->{
//                    Intent viewallintent=new Intent(TenantpropertieshomeActivity.this,TenantpropertiesMainActivity.class);
//                    startActivity(viewallintent);
//                }
//        );
            List<String> propertiesdatasub = propertiesdata.get(position);
            if (propertiesdatasub.size() > 0) {
                // Set item views based on your views and data model
                (viewHolderobject.rent1).setText(propertiesdatasub.get(0));

                viewHolderobject.building1.setImageResource(R.mipmap.download);


                viewHolderobject.description1.setText(propertiesdatasub.get(1));
                viewHolderobject.address1.setText(propertiesdatasub.get(2));

                viewHolderobject.owner1.setText(propertiesdatasub.get(3));
            }


    }

    @Override
    public int getItemCount() {
        return propertiesdata.size();
    }
}
