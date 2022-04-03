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

public class TenantpropertiesActivity extends  RecyclerView.Adapter<TenantpropertiesActivity.ViewHolder> {
//    Spinner spinner, spinner2;
//    TextView viewall;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tenantproperties);
       // ScrollView scrollView = view.findViewById(R.id.scrollView);
//        List<List<String>> lists = new ArrayList<>();
//        ArrayList<String> list=new ArrayList<String>();
//        list.add("Rs 1,00,000 / Month");
//        list.add("Semi Furnished, 1BHK Flat, 1200 sqft.");
//        list.add("Flat no:402, 4th Floor, 5th X roads, infront of Forum Mall, HSR Layout, Bengaluru.");
//        list.add("Owner: Mr. Venkatraya");
//
//        lists.add(list);
//        ArrayList<String> list2=new ArrayList<String>();
//        list2.add("Rs 15,000 / Month");
//        list2.add("Fully Furnished, 3BHK Flat, 2500 sqft.");
//        list2.add("Flat no:500, 5th Floor, RTX cross roads, infront of My friend house, Madhapur, Hyderabad.");
//        list2.add("Owner: Mr. Satya Sai");
//
//        lists.add(list2);
//        ArrayList<String> list3=new ArrayList<String>();
//        list2.add("Rs 1,20,000 / Month");
//        list2.add("Fully Furnished, 6BHK Duplex, 3000 sqft.");
//        list2.add("Door no:76/15/34, Main Road currency naga, infront of lalita jewllers, Nallamalla forest, AP.");
//        list2.add("Owner: Mr. happy");
//
//        lists.add(list3);
//        System.out.println(lists.size());
//        LinearLayout linearlayoutmain=(LinearLayout) findViewById(R.id.LinearLayoutmain);
//        int i=0;
//        for( List<String> listt: lists)
//{
//System.out.println(listt);
//if(listt.size()>0) {

//    ImageView building1=(ImageView) findViewById(R.id.building1);
//    building1.setImageResource(R.mipmap.download);
//    TextView rent1=(TextView) findViewById(R.id.rent1);
//    rent1.setText(listt.get(0));
//    TextView description1=(TextView) findViewById(R.id.description1);
//    description1.setText(listt.get(1));
//    TextView address1=(TextView) findViewById(R.id.address1);
//    address1.setText(listt.get(2));
//    TextView owner1=(TextView) findViewById(R.id.owner1);
//    owner1.setText(listt.get(3));

//    LinearLayout linearLayout = new LinearLayout(this);
//    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(380*((int) linearLayout.getResources().getDisplayMetrics().density), 600*((int) linearLayout.getResources().getDisplayMetrics().density)));
//
//    linearLayout.setOrientation(LinearLayout.VERTICAL);
//
//
//    ImageView imageview=new ImageView(this);
//    imageview.setImageResource(R.mipmap.download);
//    imageview.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//    ViewGroup.MarginLayoutParams marginparams=new ViewGroup.MarginLayoutParams(imageview.getLayoutParams());
//    marginparams.setMargins(60*((int) linearLayout.getResources().getDisplayMetrics().density),10*((int) linearLayout.getResources().getDisplayMetrics().density),10*((int) linearLayout.getResources().getDisplayMetrics().density),0*((int) linearLayout.getResources().getDisplayMetrics().density));
//    RelativeLayout.LayoutParams layoutparameter2=new RelativeLayout.LayoutParams(marginparams);
//    imageview.setLayoutParams(layoutparameter2);
//linearLayout.addView(imageview);
//
//    TextView rent1=new TextView(this);
//    rent1.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//    rent1.setText(listt.get(0));
//    rent1.setTextSize(10);
//    rent1.setTextColor(Color.parseColor("#730B0B"));
//    rent1.setLayoutParams(layoutparameter2);
//linearLayout.addView(rent1);
//
//
//    TextView description1=new TextView(this);
//    description1.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//    description1.setText(listt.get(1));
//    description1.setTextSize(8);
//    description1.setTypeface(null,Typeface.BOLD);
//
//    description1.setTextColor(Color.parseColor("#0C0101"));
//    description1.setLayoutParams(layoutparameter2);
//    linearLayout.addView(description1);
//
//
//    TextView address1=new TextView(this);
//    address1.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//    address1.setText(listt.get(2));
//    address1.setTextSize(10);
//    address1.setTextColor(Color.parseColor("#120505"));
//    address1.setLayoutParams(layoutparameter2);
//    linearLayout.addView(address1);
//
//    LinearLayout linearLayoutsub = new LinearLayout(this);
//    linearLayoutsub.setLayoutParams(layoutparameter2);
//    linearLayoutsub.setBackgroundResource(R.drawable.linearlayoutbg);
//
//    linearLayoutsub.setOrientation(LinearLayout.HORIZONTAL);
//
//    TextView owner1=new TextView(this);
//    owner1.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//    owner1.setText("Owner Mr. Venkatraya");
//    owner1.setTextSize(8);
//    owner1.setTextColor(Color.parseColor("#120505"));
//    owner1.setTypeface(null,Typeface.BOLD);
//    //owner1.setLayoutParams(layoutparameter2);
//
//    linearLayoutsub.addView(owner1);
//
//    LinearLayout linearLayoutsub2 = new LinearLayout(this);
//    linearLayoutsub2.setLayoutParams(new LinearLayout.LayoutParams(800*((int) linearLayout.getResources().getDisplayMetrics().density), 480*((int) linearLayout.getResources().getDisplayMetrics().density)));
//    linearLayoutsub2.setOrientation(LinearLayout.VERTICAL);
//
//    TextView Reachat=new TextView(this);
//    Reachat.setLayoutParams(new ViewGroup.LayoutParams(88*((int) linearLayout.getResources().getDisplayMetrics().density), LinearLayout.LayoutParams.WRAP_CONTENT));
//    Reachat.setText("Reach At:");
//    Reachat.setTextSize(8);
//    Reachat.setTextColor(Color.parseColor("#A60E0E"));
//    Reachat.setTypeface(null,Typeface.BOLD);
//  //  Reachat.setLayoutParams(layoutparameter2);
//
//    linearLayoutsub2.addView(Reachat);
//
//    LinearLayout linearLayoutsub3 = new LinearLayout(this);
//    linearLayoutsub3.setLayoutParams(new LinearLayout.LayoutParams(400*((int) linearLayout.getResources().getDisplayMetrics().density), 50*((int) linearLayout.getResources().getDisplayMetrics().density)));
//    linearLayoutsub3.setOrientation(LinearLayout.HORIZONTAL);
//
//    ImageView phoneno=new ImageView(this);
//    phoneno.setImageResource(R.mipmap.phone);
//    phoneno.setLayoutParams(new ViewGroup.LayoutParams(38*((int) linearLayout.getResources().getDisplayMetrics().density),38*((int) linearLayout.getResources().getDisplayMetrics().density)));
//
//    linearLayoutsub3.addView(phoneno);
//
//    ImageView whatsapp=new ImageView(this);
//    whatsapp.setImageResource(R.mipmap.whatsapp);
//    whatsapp.setLayoutParams(new ViewGroup.LayoutParams(38*((int) linearLayout.getResources().getDisplayMetrics().density),38*((int) linearLayout.getResources().getDisplayMetrics().density)));
//
//    linearLayoutsub3.addView(whatsapp);
//
//    linearLayoutsub2.addView(linearLayoutsub3);
//   linearLayoutsub.addView(linearLayoutsub2);
//    linearLayout.addView(linearLayoutsub);
//    linearlayoutmain.addView(linearLayout);
//
//    break;
//    if(i>0) {
//        LinearLayout linearlayoutsub = (LinearLayout) findViewById(R.id.LinearLayoutsub);
//        linearlayoutmain.removeView(linearlayoutsub);
//    linearlayoutmain.addView(linearlayoutsub);
//    }
//    i++;
//}
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

                holder.building1.setImageResource(R.mipmap.download);


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
                building12.setImageResource(R.mipmap.download2);
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