package com.example.tenantfinder_new;

public class PropertyDataUpdate {
    public  String address,city,locality,furnished,propertyname,type;
  public  String bhk,rent,sqft;
    PropertyDataUpdate(String address, String bhk, String city, String furnished, String locality, String pname, String rent, String sqft, String type)
    {
        this.address=address;
        this.bhk=bhk;
        this.city=city;
        this.furnished=furnished;

        this.locality=locality;
        this.propertyname=pname;
        this.rent=rent;
        this.sqft=sqft;

        this.type=type;

    }
}
